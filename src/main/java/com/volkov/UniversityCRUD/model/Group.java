package com.volkov.UniversityCRUD.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private long id;

    @Column(name = "group_name")
    private String groupName;
    @Column(name = "group_code")
    private String groupCode;

    //one-to-many
    @OneToMany(mappedBy = "group")
    private List<Student> students;

    //many-to-many
    @ManyToMany(mappedBy = "groups")
    private List<Subject> subjects;

}
