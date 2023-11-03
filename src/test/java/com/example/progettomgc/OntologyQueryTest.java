package com.example.progettomgc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OntologyQueryTest {

    OntologyQuery test = new OntologyQuery("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");

    @Test
    void getElements() {
        ArrayList<Element> elements = test.getElements(ProductType.Computer.name());

        assertNotNull(elements);
        elements = test.getElements(ProductType.Computer.name());

        assertNotNull(elements);
        elements = test.getElements(ProductType.Computer.name());

        assertNotNull(elements);
        elements = test.getElements(ProductType.Computer.name());

        assertNotNull(elements);
    }

    @Test
    void getElementsByProperty() {
        String property = Properties.haMarca.name(); // Sostituisci con la tua proprietà specifica.
        String propertyValue = "DELL"; // Sostituisci con il tuo valore specifico.

        ArrayList<Element> elements = test.getElementsByProperty(property, propertyValue);

        // Assicurati che il risultato non sia nullo.
        assertNotNull(elements);
    }

    @Test
    void getProperties() {

        ArrayList<Element> elements = test.getElements(ProductType.Computer.name());
        for (Element element : elements) {

            ArrayList<String> properties = test.getProperties(element);

            assertNotNull(properties);
            assertEquals(properties, element.getObjectProperties());
        }
    }
}