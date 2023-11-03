package com.example.progettomgc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ElementsTest {

    OntologyQuery ont = new OntologyQuery("negozio_elettronica.rdf", "http://unicam.it/negozio_di_elettronica#");
    Elements test = new Elements(ont);
    @Test
    void search() {
        String typeToSearch = ProductType.Telefono.name();

        ArrayList<Element> searchResult = test.search(typeToSearch);

        // Verifica che il risultato non sia nullo
        assertNotNull(searchResult);

        // Verifica che gli elementi nel risultato corrispondano al tipo cercato
        for (Element element : searchResult) {
            assertEquals(typeToSearch, element.getType());
        }
    }

    @Test
    void searchByName() {
        Element e = new Element("probook3", "http://unicam.it/negozio_di_elettronica#");

        test.add(e);

        String nameToSearch = "probook3";

        Element searchResult = test.searchByName(nameToSearch);

        // Verifica che il risultato sia diverso da null se l'elemento è stato trovato
        assertNotNull(searchResult);

        // Verifica che il nome dell'elemento nel risultato corrisponda al nome cercato
        assertEquals(nameToSearch, searchResult.getName());

        // Verifica che il metodo restituisca null se l'elemento non è stato trovato
        String nonExistentName = "prodottoNonEsistente";
        Element nonExistentResult = test.searchByName(nonExistentName);
        assertNull(nonExistentResult);
    }

    @Test
    void remove() {
        Element e = new Element("probook3", "http://unicam.it/negozio_di_elettronica#");

        test.add(e);

        String nameToSearch = "probook3";

        Element searchResult = test.searchByName(nameToSearch);

        // Verifica che il risultato sia diverso da null se l'elemento è stato trovato
        assertNotNull(searchResult);

        test.remove(e);
        searchResult = test.searchByName(nameToSearch);
        assertNull(searchResult);
    }

    @Test
    void add() {
        Element e = new Element("probook3", "http://unicam.it/negozio_di_elettronica#");

        test.add(e);

        String nameToSearch = "probook3";

        Element searchResult = test.searchByName(nameToSearch);

        // Verifica che il risultato sia diverso da null se l'elemento è stato trovato
        assertNotNull(searchResult);

        // Verifica che il nome dell'elemento nel risultato corrisponda al nome cercato
        assertEquals(nameToSearch, searchResult.getName());
    }
}