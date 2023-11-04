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
        test.deleteElement(elementToAdd);
    }

    @Test
    void deleteElement() throws FileNotFoundException {
        // Crea un oggetto Element da aggiungere all'ontologia
        Element delEl = new Element("TestDelete", "http://unicam.it/negozio_di_elettronica#");
        delEl.setType(ProductType.Accessori.name());

        test.addElement(delEl);
        //controllo prima che l elemento sia stato inserito
        ArrayList<Element> elements = ont.getElements(ProductType.Accessori.name());
        for (Element element : elements) {
            if (element.equals(delEl)) {
                assertEquals(element, delEl);
            }
        }

        test.deleteElement(delEl);
        elements.clear();

        elements = ont.getElements(ProductType.Accessori.name());
        //controllo che l elemento non sia contenuto nella lista di elementi
        assertFalse(elements.contains(delEl));
    }

    @Test
    void addObjectProperty() throws FileNotFoundException {
        // Crea un oggetto Element da aggiungere all'ontologia
        Element elementToAdd = new Element("TestElement", "http://unicam.it/negozio_di_elettronica#");
        elementToAdd.setType(ProductType.Accessori.name());

        //aggiungo l elemento
        test.addElement(elementToAdd);
        //aggiungo la object property all elemento
        test.addObjectProperty(elementToAdd, "testPrice", Properties.haPrezzo.name());
        //di conseguenza aggiungo la object property anche al elemento locale e non solo all ontologia
        elementToAdd.setObjectProperties(ont.getProperties(elementToAdd));
        //controllo che le object properties siano le stesse sia nel locale che nell ontologia
        assertEquals(ont.getProperties(elementToAdd), elementToAdd.getObjectProperties());

        test.deleteElement(elementToAdd);

    }
}