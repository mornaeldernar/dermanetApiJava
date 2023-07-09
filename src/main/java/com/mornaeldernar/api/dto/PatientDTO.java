package com.mornaeldernar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    @PositiveOrZero(message = "El id debe ser cero o un entero positivo.")
    private long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;
    @PastOrPresent(message = "La fecha de nacimiento no puede ser despues del dia de hoy")
    private Date birthdate;
    @NotBlank(message = "El sexo no puede estar vacio")
    private String sex;
    @NotBlank(message = "El telefono no puede estar vacio")
    private String phone;
    private String profesion;
}
