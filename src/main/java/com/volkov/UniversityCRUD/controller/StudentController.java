package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @GetMapping("/student/getsubjectcount/{id}")
    String getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        AtomicInteger studentSubjectsCount = new AtomicInteger();
        student.ifPresent(student1 -> studentSubjectsCount.set(getStudentSubjects(student1).size()));

        return convertToJson(studentSubjectsCount);
    }

    @GetMapping("/student/getteachersname/{id}")
    String getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        if (!student.isPresent())
            return convertToJson(new ArrayList<String>());

        AtomicReference<List<String>> teachers = new AtomicReference<>();
        student.ifPresent(student1 -> teachers.set(getStudentSubjects(student1).stream()
                .map(Subject::getTutor)
                .filter(Objects::nonNull)
                .map(Tutor::getFullName)
                .collect(Collectors.toList())));

        return convertToJson(teachers.get());
    }

    @GetMapping("/student/getteachersavgage/{id}")
    String getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        if (!student.isPresent())
            return convertToJson("Invalid Id");

        List<Integer> teachers = getStudentSubjects(student.get()).stream()
                .map(Subject::getTutor)
                .filter(Objects::nonNull)
                .map(Tutor::getAge)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return convertToJson(countAverageAge(teachers));
    }

    private List<Subject> getStudentSubjects(Student student2) {
        try {
            return student2.getGroup().getSubjects();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    private double countAverageAge(List<Integer> teacherAgeList) {
        long sumOfAges = teacherAgeList.stream().mapToLong(teacherAge -> teacherAge).sum();
        double result = sumOfAges / ((double) teacherAgeList.size());
        return Double.isNaN(result) ? 0 : result;
    }

    private String convertToJson(Object all) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(all);
    }
}
