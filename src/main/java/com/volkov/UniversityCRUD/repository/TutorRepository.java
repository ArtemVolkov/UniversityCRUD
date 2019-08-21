package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("Select stud FROM Tutor t " +
            "inner join t.subject subj " +
            "inner join subj.groups groups " +
            "inner join groups.students stud " +
            "where t.id= :id and stud.sex= :sex")
    List<Student> findTutorStudentsBySex(@Param("id") Long id, @Param("sex") String sex);

}
