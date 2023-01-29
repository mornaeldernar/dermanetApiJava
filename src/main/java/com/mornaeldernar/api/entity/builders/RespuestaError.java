package com.mornaeldernar.api.entity.builders;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class RespuestaError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int estatus;
    private Map<String, String> errores;
    private String ruta;
}
