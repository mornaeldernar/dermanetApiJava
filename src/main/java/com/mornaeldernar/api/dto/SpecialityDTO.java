package com.mornaeldernar.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SpecialityDTO {
    private long id;
    private String name;
    private Date createdAt;
    private Date modifiedAt;
}
