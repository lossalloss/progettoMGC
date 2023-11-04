package com.example.progettomgc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {

    Element test = new Element("test", "http://unicam.it/negozio_di_elettronica#");

    @Test
    void getName() {
        assertEquals("test", test.getName());
    }

    @Test
    void getFullName() {
        assertEquals("http://unicam.it/negozio_di_elettronica#"+"test", test.getFullName());
    }

    @Test
    void getType() {
        test.setType("test");
        assertEquals("test", test.getType());
    }

    @Test
    void getObjectProperties() {
        ArrayList<String> op = new ArrayList<>();
        op.add("test");
        //aggiungo la lista di OP all elemento
        test.setObjectProperties(op);

        assertEquals(op, test.getObjectProperties());
    }
}