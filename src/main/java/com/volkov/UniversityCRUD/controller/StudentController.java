package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.volkov.UniversityCRUD.Util.JsonConverter.convertToJson;

@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private final StudentRepository studentsRepository;

    public StudentController(StudentRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping("/student/all")
    private String students() throws JsonProcessingException {
        return convertToJson(studentsRepository.findAll());
    }

    @GetMapping("/student/{id}/remove")
    private String removeStudentById(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        if (!student.isPresent())
            return convertToJson("Invalid Id");

        studentsRepository.deleteById(id);
        return convertToJson(student.orElse(new Student()));
    }

    @GetMapping("/student/{id}/subject/count")
    private String getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        if (!student.isPresent())
            return convertToJson("Invalid Id");

        int studentSubjectsCount = getStudentSubjects(student.get()).size();

        return convertToJson(studentSubjectsCount);
    }

    @GetMapping("/student/{id}/teacher/names")
    private String getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentsRepository.findById(id);

        if (!student.isPresent())
            return convertToJson("Invalid Id");

        AtomicReference<List<String>> teachers = new AtomicReference<>();
        student.ifPresent(student1 -> teachers.set(getStudentSubjects(student1).stream()
                .map(Subject::getTutor)
                .filter(Objects::nonNull)
                .map(Tutor::getFullName)
                .collect(Collectors.toList())));

        return convertToJson(teachers.get());
    }

    @GetMapping("/student/{id}/teacher/avgage")
    private String getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
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

    private List<Subject> getStudentSubjects(Student student) {
        try {
            return student.getGroup().getSubjects();
        } catch (NullPointerException e) {
            LOGGER.error("Null pointer Exception in getTeacherGroup method! Tutor id=" + student.getId());
            return new ArrayList<>();
        }
    }

    private double countAverageAge(List<Integer> teacherAgeList) {
        long sumOfAges = teacherAgeList.stream().mapToLong(teacherAge -> teacherAge).sum();
        double result = sumOfAges / ((double) teacherAgeList.size());
        return Double.isNaN(result) ? 0 : result;
    }
}
