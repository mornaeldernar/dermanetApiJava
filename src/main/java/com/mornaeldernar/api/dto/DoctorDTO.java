package com.mornaeldernar.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DoctorDTO {

    private long id;
    private String name;
    private String lastName;
    private Date createdAt;
    private Date modifiedAt;
    private SpecialityDTO speciality;
}
