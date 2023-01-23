package com.mornaeldernar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private long id;
    private String name;
    private String lastName;
    private Date birthdate;
}
