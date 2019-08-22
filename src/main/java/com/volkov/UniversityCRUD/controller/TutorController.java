package com.volkov.UniversityCRUD.controller;

import com.volkov.UniversityCRUD.Service.TutorService;
import com.volkov.UniversityCRUD.model.ReturnMessage;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.model.dto.TutorDTO;
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
    public List<TutorDTO> getAllTutors() {
        return tutorService.findAllTeachers();
    }

    @GetMapping("/tutor/{id}/remove")
    public ReturnMessage removeTutorById(@PathVariable @NotNull @DecimalMin("1") Long id) {
        Optional<TutorDTO> tutorOptional = tutorService.findTutorById(id);

        if (!tutorOptional.isPresent())
            return ReturnMessage.getInstanceOfFailMessage("Can`t find tutor with this ID!");

        tutorService.deleteTeacherById(id);
        return ReturnMessage.getInstanceOfSuccessMessage(tutorOptional.get());
    }

    @GetMapping("/tutor/{id}/students/males")
    public List<StudentUpdateDTO> getTutorMaleStudents(@PathVariable @NotNull @DecimalMin("1") Long id) {
        return tutorService.findTeacherMaleStudent(id);
    }
}
