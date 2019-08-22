package com.volkov.UniversityCRUD.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class SubjectDTO {

    @JsonProperty("Subject title")
    private String subjectTitle;

    @JsonProperty("Subject code")
    private String subjectCode;

}
