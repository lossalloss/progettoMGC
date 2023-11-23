package com.example.progettomgc;

import java.util.ArrayList;

public class Element implements ElementInterface {

    private String name;
    private String fullName;
    private String type;
    private ArrayList<String> objectProperties;

    public Element(String name, String namespace) {
        this.name = name;
        this.fullName = namespace+name;
        objectProperties = new ArrayList<>();
    }

    /**
     * Ritorna il nome dell'elemento
     *
     * @return  nome dell'elemento
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Ritorna il nome completo dell'elemento,
     * ovvero con il namespace
     *
     * @return  nome dell'elemento completo
     */
    @Override
    public String getFullName() {
        return fullName;
    }

    /**
     * Ritorna il tipo dell'elemento
     *
     * @return  tipo dell'elemento
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Imposta il tipo dell'elemento
     *
     * @param type tipo dell'elemento
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Ritorna una lista contenente le proprietà dell'elemento
     *
     * @return  ArrayList<String> delle proprietà dell'elemento
     */
    @Override
    public ArrayList<String> getObjectProperties() {
        return objectProperties;
    }

    /**
     * Imposta le proprietà dell'elemento
     *
     * @param op Lista delle proprietà dell'elemento
     */
    @Override
    public void setObjectProperties(ArrayList<String> op) {
        objectProperties = op;
    }
}
