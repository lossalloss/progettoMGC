package com.example.progettomgc;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface QueryInterrogator<T> {
    ArrayList<T> getElements(String type);
    ArrayList<T> getElementsByProperty(String property, String propertyValue);
    ArrayList<T> getProperties(T element);
}
