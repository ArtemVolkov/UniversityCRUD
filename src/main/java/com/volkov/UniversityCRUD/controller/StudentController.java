package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.UniversityCRUD.Service.StudentService;
import com.volkov.UniversityCRUD.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.volkov.UniversityCRUD.Util.JsonConverter.convertToJson;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/all")
    public String students() throws JsonProcessingException {
        return convertToJson(studentService.findAllStudent());
    }

    @GetMapping("/student/{id}/remove")
    public String removeStudentById(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Student> student = studentService.findById(id);

        if (!student.isPresent())
            return convertToJson("Invalid Id");

        studentService.deleteStudent(student.get());
        return convertToJson(student.orElse(new Student()));
    }

    @GetMapping("/student/{id}/subject/count")
    public String getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        return convertToJson(studentService.getStudentSubjectCount(id));
    }

    @GetMapping("/student/{id}/teacher/names")
    public String getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        return convertToJson(studentService.getStudentTeachersAverageAge(id));
    }

    @GetMapping("/student/{id}/teacher/avgage")
    public String getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        return convertToJson(studentService.getStudentTeachersAverageAge(id));
    }
}
