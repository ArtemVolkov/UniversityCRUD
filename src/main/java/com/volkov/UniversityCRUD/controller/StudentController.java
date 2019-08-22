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
import java.util.Optional;

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
        Optional<StudentUpdateDTO> studentDTO = studentService.findById(id);

        if (!studentDTO.isPresent())
            return ReturnMessage.getInstanceOfFailMessage("Can`t find student with this ID!");

        studentService.deleteStudentById(studentDTO.get().getId());
        return ReturnMessage.getInstanceOfSuccessMessage(studentDTO.get());
    }

    @GetMapping("/student/{id}/subject/count")
    public ReturnMessage getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage.getInstanceOfSuccessMessage
                ("Student with id " + id + " subjects count = " + studentService.getStudentSubjectCount(id));
    }

    @GetMapping("/student/{id}/teacher/names")
    public ReturnMessage getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage.getInstanceOfSuccessMessage(studentService.getStudentTeacherNameList(id));
    }

    @GetMapping("/student/{id}/teacher/avgage")
    public ReturnMessage getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return ReturnMessage.getInstanceOfSuccessMessage
                ("Student with id " + id + " teachers average age = " + studentService.getStudentTeachersAverageAge(id));
    }
}
