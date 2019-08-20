package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentsRepository;

    @GetMapping("/")
    String students() throws JsonProcessingException {
        List<Student> all = studentsRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(all);

    }
}
