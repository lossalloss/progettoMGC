package com.example.progettomgc;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTest {

    Modifier test = new Modifier("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");
    OntologyQuery ont = new OntologyQuery("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");

    @Test
    void addElement() throws FileNotFoundException {
        // Crea un oggetto Element da aggiungere all'ontologia
        Element elementToAdd = new Element("TestElement", "http://unicam.it/negozio_di_elettronica#");
        elementToAdd.setType(ProductType.Accessori.name());

        test.addElement(elementToAdd);

        ArrayList<Element> elements = ont.getElements(ProductType.Accessori.name());
        for (Element element : elements) {
            if (element.equals(elementToAdd)) {
                assertEquals(element, elementToAdd);
            }
        }
    }

    @Test
    void deleteElement() {
    }

    @Test
    void addObjectProperty() {
    }
}