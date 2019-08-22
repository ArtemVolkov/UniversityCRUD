package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.UniversityCRUD.Service.TutorService;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Tutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping("/tutor/all")
    public List<Tutor> getAllTutors() {
        return tutorService.findAllTeachers();
    }

    @GetMapping("/tutor/{id}/remove")
    public Tutor removeTutorById(@PathVariable @NotNull @DecimalMin("1") Long id) {
        Optional<Tutor> tutorOptional = tutorService.findTutorById(id);

        if (!tutorOptional.isPresent())
            return new Tutor();

        tutorService.deleteTeacherById(id);
        return tutorOptional.get();
    }

    @GetMapping("/tutor/{id}/students/males")
    public List<Student> getTutorMaleStudents(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return tutorService.findTeacherMaleStudent(id);
    }
}
