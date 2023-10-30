package com.example.progettomgc;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface localDB<T> {
    ArrayList<T> search(String type);

    T searchByName(String name);
    void remove(T e);
    void add(T e);
}
