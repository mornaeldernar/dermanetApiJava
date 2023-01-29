package com.mornaeldernar.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {
    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "Cancer";
    private static String nombre2 = "Cancer2";
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Patient patient;

    @BeforeEach
    void init(){
        patient = new Patient(id,nombre, "Perez",null,fecha,fecha, null);
    }
    @Test
    void getId() {
        assertEquals(id,patient.getId());
    }

    @Test
    void getName() {
        assertEquals(nombre,patient.getName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,patient.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,patient.getModifiedAt());
    }

    @Test
    void setIdTest() {
        patient.setId(id2);
        assertEquals(id2,patient.getId());
    }

    @Test
    void setNameTest() {
        patient.setName(nombre2);
        assertEquals(nombre2,patient.getName());
    }

    @Test
    void setCreatedAtTest() {
        patient.setCreatedAt(fecha2);
        assertEquals(fecha2,patient.getCreatedAt());
    }

    @Test
    void setModifiedAtTest() {
        patient.setModifiedAt(fecha2);
        assertEquals(fecha2,patient.getModifiedAt());
    }
}