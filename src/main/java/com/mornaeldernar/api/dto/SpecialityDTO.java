package com.mornaeldernar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {
    @PositiveOrZero(message = "El id debe ser cero o un entero positivo.")
    private long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
}
