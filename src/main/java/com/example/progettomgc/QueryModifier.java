package com.example.progettomgc;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface QueryModifier<T> {
    void addElement(T element) throws FileNotFoundException;
    void deleteElement(T element) throws FileNotFoundException;
    void addObjectProperty(T element, String propertyValue, String property) throws FileNotFoundException;
}
