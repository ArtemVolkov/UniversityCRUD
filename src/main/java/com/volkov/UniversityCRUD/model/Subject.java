package com.volkov.UniversityCRUD.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private long id;
    @Column(name = "subject_title")
    private String subjectTitle;
    @Column(name = "subject_code")
    private String subjectCode;

    //one-to-one
    @OneToOne(mappedBy = "subject", cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="#tutor_id")
    private Tutor tutor;

    //many-to-many
    @ManyToMany
    @JoinTable(name = "subjects_groups",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="#group_id")
    private List<Group> groups;


}
