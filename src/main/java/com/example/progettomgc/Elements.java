package com.example.progettomgc;

import java.util.ArrayList;
import java.util.Objects;

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
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).getType().equals(type)){
                res.add(elements.get(i));
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
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).getName().equals(name)){
                Element res = elements.get(i);
                return res;
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
}
