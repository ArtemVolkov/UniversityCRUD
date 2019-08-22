package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.repository.interfaces.StudentRepositoryCustomInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long>, QuerydslPredicateExecutor<Student>, StudentRepositoryCustomInterface {

    @Query("select avg(teach.age) from Tutor teach " +
            "inner join teach.subject subj " +
            "inner join subj.groups gr " +
            "inner join gr.students stud " +
            "where stud.id= :id")
    Double getStudentTeachersAverageAge(@Param("id") Long id);
}
