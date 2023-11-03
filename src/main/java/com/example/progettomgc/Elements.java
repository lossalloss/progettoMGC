package com.example.progettomgc;

import java.util.ArrayList;

public class Elements implements localDB{

    private ArrayList<Element> elements;
    private OntologyQuery ask;

    public Elements(OntologyQuery ask) {
        elements = new ArrayList<>();
        this.ask = ask;
    }

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

    @Override
    public Element searchByName(String name) {
        for (Element element : elements) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }


    @Override
    public void remove(Object e){
        elements.remove(e);
    }

    @Override
    public void add(Object e) {
        elements.add((Element) e);
    }

    @Override
    public void addAll(ArrayList e) {
        elements.addAll(e);
    }

    @Override
    public Object get(int i) {
        return elements.get(i);
    }


}
