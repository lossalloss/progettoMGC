package com.example.progettomgc;

import java.util.ArrayList;

public class Elements implements localDB{

    private ArrayList<Element> elements;
    private OntologyQuery ask;

    public Elements(OntologyQuery ask) {
        elements = new ArrayList<>();
        this.ask = ask;
    }

    /**
     * Questo metodo permette cercare nel database locale l'eventuale presenza di elementi
     * del tipo specificato. Nel caso in cui non siano presenti gli elementi verranno
     * ritornati interrogando l'ontologia
     *
     * @param type tipo da ricercare
     * @return una lista di elementi del tipo specificato
     */
    @Override
    public ArrayList<Element> search(String type) {
        ArrayList<Element> res = new ArrayList<>();
        for (Element element : elements) {
            if (element.getType().equals(type)) {
                res.add(element);
            }
        }
        //interrogo il database solo se non ho già salvato i risultati in locale
        if(res.size()==0){
            res = ask.getElements(type);
            //inserisco la lista presa dall ontologia nella lista locale
            elements.addAll(res);
        }
        return res;
    }

    /**
     * Questo metodo permette cercare nel database locale un elemento dato il nome
     *
     * @param name Nome dell'elemento da ricercare
     * @return l'elemento con il nome dato oppure null se non è presente
     */
    @Override
    public Element searchByName(String name) {
        for (Element element : elements) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Questo metodo permette di eliminare un elemento dal database locale
     *
     * @param element elemento da eliminare
     */
    @Override
    public void remove(Object element){
        checkType(element);
        elements.remove(element);
    }

    /**
     * Questo metodo permette di aggiungere un elemento al database locale
     *
     * @param element elemento da aggiungere
     */
    @Override
    public void add(Object element) {
        checkType(element);
        elements.add((Element) element);
    }

    /**
     * Questo metodo permette di aggiungere una lista di elementi
     * al database locale
     *
     * @param elements Lista di elementi da aggiungere
     */
    @Override
    public void addAll(ArrayList elements) {
        elements.addAll(elements);
    }

    /**
     * Questo metodo permette di ritornare l'elemento nella posizione richiesta
     * con il parametro i
     *
     * @param i posizione dell'elemento che si vuole ritornare
     * @return L'elemento nella posizione i
     */
    @Override
    public Object get(int i) {
        return elements.get(i);
    }

    private void checkType(Object t){
        if (!(t instanceof Element)) {
            throw new IllegalArgumentException("Il parametro element deve essere istanza di ELement!");
        }
    }
}
