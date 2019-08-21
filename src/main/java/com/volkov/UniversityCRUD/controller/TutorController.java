package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.UniversityCRUD.Service.TutorService;
import com.volkov.UniversityCRUD.model.Tutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.volkov.UniversityCRUD.Util.JsonConverter.convertToJson;

@RestController
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping("/tutor/all")
    public String getAllTutors() throws JsonProcessingException {
        return convertToJson(tutorService.findAllTeachers());
    }

    @GetMapping("/tutor/{id}/remove")
    public String removeTutorById(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Tutor> tutorOptional = tutorService.findTutorById(id);

        if (!tutorOptional.isPresent())
            return convertToJson("Invalid tutor id");

        tutorService.deleteTeacherById(id);
        return convertToJson(tutorOptional.get());
    }

    @GetMapping("/tutor/{id}/students/males")
    public String getTutorMaleStudents(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        return convertToJson(tutorService.findTeacherMaleStudent(id));
    }
}
