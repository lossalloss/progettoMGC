package com.example.progettomgc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTest {

    Modifier test = new Modifier("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");
    OntologyQuery ont = new OntologyQuery("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");

    Elements el = new Elements(ont);
    @Test
    void addElement() throws FileNotFoundException {
        // Crea un oggetto Element da aggiungere all'ontologia
        Element elementToAdd = new Element("TestElement", ProductType.Accessori.name());

        test.addElement(elementToAdd);

        ont.getElements(ProductType.Accessori.name());
        //da finire
    }

    @Test
    void deleteElement() {
    }

    @Test
    void addObjectProperty() {
    }
}