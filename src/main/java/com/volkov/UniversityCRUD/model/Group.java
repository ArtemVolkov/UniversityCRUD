package com.volkov.UniversityCRUD.model;


import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;
    @Column(name = "group_code")
    private String groupCode;

    //one-to-many
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<Student> students;

    //many-to-many
    @ManyToMany(mappedBy = "groups")
    private List<Subject> subjects;

}
