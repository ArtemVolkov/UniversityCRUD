package com.volkov.UniversityCRUD.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@ToString
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    @JsonProperty("Student id")
    private long id;

    @Column(name = "full_name")
    @JsonProperty("Full Name")
    private String fullName;
    @Column(name = "phone")
    @JsonProperty("Phone")
    private String phone;

    //many-to-one
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="#id")
    //@JsonIgnore
    private Group group;


}
