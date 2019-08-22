package com.volkov.UniversityCRUD.repository.interfaces;

import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;

import java.util.List;

public interface TutorRepositoryCustomInterface {

    List<StudentUpdateDTO> findTeacherMaleStudent2(Long id);
}
