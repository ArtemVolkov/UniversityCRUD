package com.volkov.UniversityCRUD.controller;

import com.volkov.UniversityCRUD.Service.StudentService;
import com.volkov.UniversityCRUD.model.ReturnMessage;
import com.volkov.UniversityCRUD.model.dto.GroupDTO;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/student/{id}/update")
    public ReturnMessage updateStudent(@PathVariable @NotNull @DecimalMin("1") Long id,
                                       @RequestBody StudentUpdateDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @PostMapping("/student/createDTO")
    public StudentUpdateDTO createDTO() {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupCode("100");
        groupDTO.setGroupName("Changed");

        StudentUpdateDTO studentDTO = new StudentUpdateDTO();
        studentDTO.setId(6L);
        studentDTO.setGroup(groupDTO);
        studentDTO.setFullName("Hi i`m changed");
        studentDTO.setPhone("330");
        studentDTO.setSex("Male");
        studentDTO.setMark(3.3D);
        studentDTO.setAge(10);

        return studentDTO;
    }
}
