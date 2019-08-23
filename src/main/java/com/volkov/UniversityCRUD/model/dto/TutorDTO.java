package com.volkov.UniversityCRUD.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class TutorDTO {

    @JsonProperty("Teacher id")
    private Long id;

    @JsonProperty("Full Name")
    private String fullName;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Age")
    private Integer age;

    @JsonProperty("Subject")
    private SubjectDTO subject;
}
