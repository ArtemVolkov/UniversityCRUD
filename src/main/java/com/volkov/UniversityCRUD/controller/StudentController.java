package com.volkov.UniversityCRUD.controller;

import com.volkov.UniversityCRUD.Service.StudentService;
import com.volkov.UniversityCRUD.model.ReturnMessage;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/all")
    public List<StudentUpdateDTO> students() {
        return studentService.findAllStudent();
    }

    @GetMapping("/student/{id}/remove")
    public ReturnMessage removeStudentById(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/student/{id}/subject/count")
    public ReturnMessage getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage
                .builder()
                .success(true)
                .message("Student with id " + id + " subjects count = " + studentService.getStudentSubjectCount(id))
                .build();
    }

    @GetMapping("/student/{id}/teacher/names")
    public ReturnMessage getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage
                .builder()
                .success(true)
                .message(studentService.getStudentTeacherNameList(id))
                .build();
    }

    @GetMapping("/student/{id}/teacher/avgage")
    public ReturnMessage getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage
                .builder()
                .success(true)
                .message("Student with id " + id + " teachers average age = " + studentService.getStudentTeachersAverageAge(id))
                .build();
    }
}
