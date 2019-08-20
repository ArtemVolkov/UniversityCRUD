package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
