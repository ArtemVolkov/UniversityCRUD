package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentsRepository;

    @GetMapping("/student/all")
    String students() throws JsonProcessingException {
        List<Student> all = studentsRepository.findAll();
        return convertToJson(all);
    }

    @GetMapping("/student/remove/{id}")
    String removeStudentById(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);
        if (student.isPresent())
            studentsRepository.deleteById(id);

        return convertToJson(student.orElse(new Student()));
    }

    private String convertToJson(Object all) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(all);
    }
}
