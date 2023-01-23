package com.mornaeldernar.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private static Long id = 1l;
    private static Long id2 = 2l;
    private static String nombre = "ADMIN";
    private static String nombre2 = "USER";
    private static boolean active = true;
    private static boolean inactive = false;
    private static Date fecha = new Date(2023,01,01);
    private static Date fecha2 = new Date(2023,01,02);

    private Role role;

    @BeforeEach
    void init(){
        role = new Role(id,nombre, active, fecha, fecha);
    }

    @Test
    void getId() {
        assertEquals(id,role.getId());
    }

    @Test
    void getRole() {
        assertEquals(nombre,role.getRole());
    }

    @Test
    void isEnabled() {
        assertEquals(active,role.isEnabled());
    }

    @Test
    void getCreatedAt() {
        assertEquals(fecha,role.getCreatedAt());
    }

    @Test
    void getModifiedAt() {
        assertEquals(fecha,role.getModifiedAt());
    }

    @Test
    void setId() {
        role.setId(id2);
        assertEquals(id2,role.getId());
    }

    @Test
    void setRole() {
        role.setRole(nombre2);
        assertEquals(nombre2,role.getRole());
    }

    @Test
    void setEnabled() {
        role.setEnabled(inactive);
        assertEquals(inactive,role.isEnabled());
    }

    @Test
    void setCreatedAt() {
        role.setCreatedAt(fecha2);
        assertEquals(fecha2,role.getCreatedAt());
    }

    @Test
    void setModifiedAt() {
        role.setModifiedAt(fecha2);
        assertEquals(fecha2,role.getModifiedAt());
    }
}