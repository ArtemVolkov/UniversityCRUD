package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
