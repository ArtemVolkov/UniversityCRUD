package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.repository.interfaces.StudentRepositoryCustomInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StudentRepository extends JpaRepository<Student,Long>, QuerydslPredicateExecutor<Student>, StudentRepositoryCustomInterface {
}
