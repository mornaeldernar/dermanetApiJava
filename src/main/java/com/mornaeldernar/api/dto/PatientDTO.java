package com.mornaeldernar.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private long id;
    private String name;
    private String lastName;
    private Date birthdate;
    private Date createdAt;
    private Date modifiedAt;
}
