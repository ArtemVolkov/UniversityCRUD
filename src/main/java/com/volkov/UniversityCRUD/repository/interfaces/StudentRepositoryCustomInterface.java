package com.volkov.UniversityCRUD.repository.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryCustomInterface {

    long getStudentSubjectCount(Long id);

    List<String> getStudentTeacherNameList(Long id);
}
