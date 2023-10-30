package com.example.progettomgc;

import java.util.ArrayList;

public interface ElementInterface {
    String getName();

    String getFullName();

    String getType();

    void setType(String type);

    ArrayList<String> getObjectProperties();

    void setObjectProperties(ArrayList<String> op);
}
