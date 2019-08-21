package com.volkov.UniversityCRUD.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TutorDTO {

    private long id;

    private String fullName;

    private String phone;

    private Integer age;
}
