package com.volkov.UniversityCRUD.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    @JsonProperty("Student id")
    private Long id;

    @Column(name = "full_name")
    @JsonProperty("Full Name")
    private String fullName;
    @Column(name = "phone")
    @JsonProperty("Phone")
    private String phone;

    @Column(name = "mark")
    @JsonProperty("Mark")
    private Double mark;

    @Column(name = "age")
    @JsonProperty("Age")
    private Integer age;

    @Column(name = "sex")
    @JsonProperty("Sex")
    private String sex;

    //many-to-one
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "group_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "#id")
    //@JsonIgnore
    private Group group;


}
