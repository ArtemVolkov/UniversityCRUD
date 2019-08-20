package com.volkov.UniversityCRUD.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @Column(name = "tutor_id")
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;

    //one-to-one
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "tutor_id", referencedColumnName = "tutor_id")
    private Subject subject;


}
