package com.volkov.UniversityCRUD.controller;

import com.volkov.UniversityCRUD.Service.StudentService;
import com.volkov.UniversityCRUD.model.Student;
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
    public List<Student> students() {
        return studentService.findAllStudent();
    }

    @GetMapping("/student/{id}/remove")
    public Student removeStudentById(@PathVariable @NotNull @DecimalMin("1") Long id) {
        Optional<Student> student = studentService.findById(id);

        if (!student.isPresent())
            return new Student();

        studentService.deleteStudent(student.get());
        return student.orElse(new Student());
    }

    @GetMapping("/student/{id}/subject/count")
    public long getStudentSubjectsCount(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return studentService.getStudentSubjectCount(id);
    }

    @GetMapping("/student/{id}/teacher/names")
    public List<String> getStudentTeachersName(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return studentService.getStudentTeacherNameList(id);
    }

    @GetMapping("/student/{id}/teacher/avgage")
    public double getStudentTeachersAverageAge(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return studentService.getStudentTeachersAverageAge(id);
    }
}
