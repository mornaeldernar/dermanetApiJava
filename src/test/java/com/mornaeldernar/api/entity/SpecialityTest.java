package com.mornaeldernar.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialityTest {
    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "Dermatology";
    private static String nombre2 = "Dermatologia";
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Speciality speciality;

    @BeforeEach
    void init(){
        speciality = new Speciality(id,nombre, fecha,fecha);
    }
    @Test
    void getId() {
        assertEquals(id,speciality.getId());
    }

    @Test
    void getName() {
        assertEquals(nombre,speciality.getName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,speciality.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,speciality.getModifiedAt());
    }

    @Test
    void setIdTest() {
        speciality.setId(id2);
        assertEquals(id2,speciality.getId());
    }

    @Test
    void setNameTest() {
        speciality.setName(nombre2);
        assertEquals(nombre2,speciality.getName());
    }

    @Test
    void setCreatedAtTest() {
        speciality.setCreatedAt(fecha2);
        assertEquals(fecha2,speciality.getCreatedAt());
    }

    @Test
    void setModifiedAtTest() {
        speciality.setModifiedAt(fecha2);
        assertEquals(fecha2,speciality.getModifiedAt());
    }
}