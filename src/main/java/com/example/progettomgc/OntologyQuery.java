package com.example.progettomgc;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;

import java.util.ArrayList;

public class OntologyQuery implements QueryInterrogator {
    private static String ONTOLOGY_PATH;
    private static String NAMESPACE;

    public OntologyQuery(String ontologypath, String namespace) {
        ONTOLOGY_PATH = ontologypath;
        NAMESPACE = namespace;
    }

    private Model prepModel(){
        Model model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, ONTOLOGY_PATH);
        return model;
    }

    /**
     * Questo metodo ritorna un ArrayList di elementi dato il tipo di elemento che si vuole ritornare
     *
     * @param type tipo degli elementi da ritornare
     * @return ArrayList di elementi
     */
    @Override
    public ArrayList<Element> getElements(String type) {
        Model model = prepModel();

        String queryText = String.format(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX ont: <%s>\n" +
                        "SELECT ?individuo\n" +
                        "WHERE {\n" +
                        "  ?individuo rdf:type ont:%s .\n" +
                        "}", NAMESPACE, type);

        Query query = QueryFactory.create(queryText);
        ArrayList<Element> elements = new ArrayList<>();

        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode individuo = solution.get("individuo");
                String individuoURI = individuo.toString().replace(NAMESPACE, "");
                Element el = new Element(individuoURI, NAMESPACE);
                el.setObjectProperties(getProperties(individuoURI));
                el.setType(type);
                elements.add(el);
            }
        }

        return elements;
    }

    /**
     * Questo metodo ritorna un ArrayList di elementi dato il nome della proprietà e il valore della data proprietà
     *
     * @param property nome della proprietà richiesta
     * @param propertyValue valore della proprietà richiesta
     * @return ArrayList di elementi che hanno quel tipo di proprietà e quel valore per il tipo di proprietà richiesto
     */
    @Override
    public ArrayList<Element> getElementsByProperty(String property, String propertyValue) {
        Model model = prepModel();

        String queryText = String.format(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX ont: <%s>\n" +
                        "SELECT ?individuo\n" +
                        "WHERE {\n" +
                        "  ?individuo ont:%s ont:%s.\n" +
                        "}", NAMESPACE, property, propertyValue);

        Query query = QueryFactory.create(queryText);
        ArrayList<Element> elements = new ArrayList<>();

        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode individuo = solution.get("individuo");
                String individuoURI = individuo.toString().replace(NAMESPACE, "");
                Element e = new Element(individuoURI, NAMESPACE);
                e.setObjectProperties(getProperties(individuoURI));
                elements.add(e);
            }
        }

        return elements;
    }

    /**
     * Questo metodo ritorna un ArrayList di tutte le proprietà di un dato elemento
     *
     * @param element elemento da aggiungere
     * @return ArrayList contenente tutte le proprietà di un dato elemento
     */
    @Override
    public ArrayList<String> getProperties(Object element) {
        if (!(element instanceof Element)) {
            throw new IllegalArgumentException("Il parametro element deve essere istanza di Element.");
        }
        Element e = (Element) element;
        return getProperties(e.getName());
    }

    private ArrayList<String> getProperties(String element) {
        Model model = prepModel();

        Resource individuo = model.getResource(NAMESPACE+element);
        ArrayList<String> ret = new ArrayList<>();

        StmtIterator stmtIterator = individuo.listProperties();
        while (stmtIterator.hasNext()) {
            Statement statement = stmtIterator.next();
            Property property = statement.getPredicate();
            RDFNode value = statement.getObject();
            String res = (property.getURI() + ": " +value.toString()).replace(NAMESPACE, "");
            if(!res.contains("http://www.w3.org/1999/02/22-rdf-syntax-ns#")){
                ret.add(res);
            }
        }

        return ret;
    }
}
