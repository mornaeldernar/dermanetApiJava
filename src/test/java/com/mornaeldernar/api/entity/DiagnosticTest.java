package com.mornaeldernar.api.entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticTest {
    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "Cancer";
    private static String nombre2 = "Cancer2";
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Diagnostic diagnostic;

    @BeforeEach
    void init(){
        diagnostic = new Diagnostic(id,nombre, fecha,fecha);
    }
    @Test
    void getId() {
        assertEquals(id,diagnostic.getId());
    }

    @Test
    void getName() {
        assertEquals(nombre,diagnostic.getName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,diagnostic.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,diagnostic.getModifiedAt());
    }

    @Test
    void setIdTest() {
        diagnostic.setId(id2);
        assertEquals(id2,diagnostic.getId());
    }

    @Test
    void setNameTest() {
        diagnostic.setName(nombre2);
        assertEquals(nombre2,diagnostic.getName());
    }

    @Test
    void setCreatedAtTest() {
        diagnostic.setCreatedAt(fecha2);
        assertEquals(fecha2,diagnostic.getCreatedAt());
    }

    @Test
    void setModifiedAtTest() {
        diagnostic.setModifiedAt(fecha2);
        assertEquals(fecha2,diagnostic.getModifiedAt());
    }
}