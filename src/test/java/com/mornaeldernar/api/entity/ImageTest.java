package com.mornaeldernar.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest {
    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "Cancer";
    private static String nombre2 = "Cancer2";
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Image image;

    @BeforeEach
    void init(){
        image = new Image(id,nombre,"", fecha,fecha, null);
    }
    @Test
    void getId() {
        assertEquals(id,image.getId());
    }

    @Test
    void getName() {
        assertEquals(nombre,image.getName());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,image.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,image.getModifiedAt());
    }

    @Test
    void setIdTest() {
        image.setId(id2);
        assertEquals(id2,image.getId());
    }

    @Test
    void setNameTest() {
        image.setName(nombre2);
        assertEquals(nombre2,image.getName());
    }

    @Test
    void setCreatedAtTest() {
        image.setCreatedAt(fecha2);
        assertEquals(fecha2,image.getCreatedAt());
    }

    @Test
    void setModifiedAtTest() {
        image.setModifiedAt(fecha2);
        assertEquals(fecha2,image.getModifiedAt());
    }
}