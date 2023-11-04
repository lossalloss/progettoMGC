package com.example.progettomgc;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Modifier implements QueryModifier{

    private static String ONTOLOGY_PATH;
    private static String NAMESPACE;

    public Modifier(String ontologypath, String namespace) {
        ONTOLOGY_PATH = ontologypath;
        NAMESPACE = namespace;
    }

    private Model prepModel(){
        Model model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, ONTOLOGY_PATH);
        return model;
    }


    /**
     * Questo metodo permette di aggiungere un elemento insieme al suo tipo
     *
     * @param element oggetto Element da aggiungere
     */
    @Override
    public void addElement(Object element) throws FileNotFoundException {
        // Carica l'ontologia dal file
        Model model = prepModel();
        Element e = (Element) element;
        // Crea le risorse per il nuovo individuo
        Resource nuovoIndividuo = model.createResource(e.getFullName());

        // Aggiungi le triple per il nuovo individuo
        model.add(nuovoIndividuo, RDF.type, model.createResource(NAMESPACE + e.getType()));

        // Salva le modifiche nell'ontologia
        model.write(new FileOutputStream(ONTOLOGY_PATH), "RDF/XML");

    }

    /**
     * Questo metodo permette di eliminare un elemento
     *
     * @param element elemento da eliminare
     */
    @Override
    public void deleteElement(Object element) throws FileNotFoundException {
        if (!(element instanceof Element)) {
            throw new IllegalArgumentException("Il parametro element deve essere istanza di Element.");
        }
        Element e = (Element) element;
        // Carica l'ontologia dal file
        Model model = prepModel();
        String queryString = "DELETE WHERE "
                + "  { <" + e.getFullName() + "> ?p ?o }";

        UpdateAction.parseExecute(queryString, model);

        model.write(new FileOutputStream(ONTOLOGY_PATH), "RDF/XML");
    }


    private void addObjectProperty(String element, String otherElement, String property) throws FileNotFoundException {

        Model model = prepModel();
        Resource individuo = model.createResource(NAMESPACE+element);
        Resource otherindividuo = model.createResource(NAMESPACE+otherElement);

        Property proprietà = model.createProperty(NAMESPACE + property);

        individuo.addProperty(proprietà, otherindividuo);

        // Salva le modifiche nell'ontologia
        model.write(new FileOutputStream(ONTOLOGY_PATH), "RDF/XML");

    }
    /**
     * Questo metodo permette di aggiungere una ObjectProperty a un elemento
     *
     * @param element elemento a cui aggiungere la proprietà
     * @param propertyValue nome del valore della proprietà
     * @param property nome della object property da aggiungere
     */
    @Override
    public void addObjectProperty(Object element, String propertyValue, String property) throws FileNotFoundException {
        //controllo che element sia un istanza si Element
        if (!(element instanceof Element)) {
            throw new IllegalArgumentException("Il parametro element deve essere istanza di ELement!");
        }

        Element sourceElement = (Element) element;
        addObjectProperty(sourceElement.getName(), propertyValue, property);
    }
}
