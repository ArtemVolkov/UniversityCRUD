package com.volkov.UniversityCRUD.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode
public class StudentUpdateDTO {

        @Id
        @NotNull
        @JsonProperty("Student id")
        private Long id;

        @NotNull
        @JsonProperty("Full Name")
        private String fullName;

        @JsonProperty("Phone")
        private String phone;

        @JsonProperty("Mark")
        private Double mark;

        @JsonProperty("Age")
        private Integer age;

        @JsonProperty("Sex")
        private String sex;

        @JsonProperty("Group")
        private GroupDTO group;
}


