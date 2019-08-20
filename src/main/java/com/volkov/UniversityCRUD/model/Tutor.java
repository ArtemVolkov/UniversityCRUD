package com.volkov.UniversityCRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue
    @Column(name = "tut_id")
    @JsonProperty("Tutor id")
    private long id;
    @Column(name = "full_name")
    @JsonProperty("Full name")
    private String fullName;
    @Column(name = "phone")
    @JsonProperty("Phone")
    private String phone;

    @Column(name = "age")
    @JsonProperty("Age")
    private Integer age;

    //one-to-one
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "subject_id")
    private Subject subject;


}
