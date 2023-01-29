package com.mornaeldernar.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorTest {
    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "Juan";
    private static String apellido = "Perez";
    private static String nombre2 = "Cancer2";
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Doctor doctor;

    @BeforeEach
    void init(){
        doctor = new Doctor(id,nombre,apellido, fecha,fecha, null,null);
    }
    @Test
    void getId() {
        assertEquals(id,doctor.getId());
    }

    @Test
    void getName() {
        assertEquals(nombre,doctor.getName());
    }
    @Test
    void getApellido() {
        assertEquals(apellido,doctor.getLastName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,doctor.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,doctor.getModifiedAt());
    }

    @Test
    void setIdTest() {
        doctor.setId(id2);
        assertEquals(id2,doctor.getId());
    }

    @Test
    void setNameTest() {
        doctor.setName(nombre2);
        assertEquals(nombre2,doctor.getName());
    }

    @Test
    void setCreatedAtTest() {
        doctor.setCreatedAt(fecha2);
        assertEquals(fecha2,doctor.getCreatedAt());
    }

    @Test
    void setModifiedAtTest() {
        doctor.setModifiedAt(fecha2);
        assertEquals(fecha2,doctor.getModifiedAt());
    }
}