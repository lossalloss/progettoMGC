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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ArrayList<String> getObjectProperties() {
        return objectProperties;
    }

    @Override
    public void setObjectProperties(ArrayList<String> op) {
        objectProperties = op;
    }
}
