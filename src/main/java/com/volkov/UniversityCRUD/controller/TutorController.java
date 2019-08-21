package com.volkov.UniversityCRUD.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.repository.TutorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.volkov.UniversityCRUD.Util.JsonConverter.convertToJson;

@RestController
public class TutorController {

    private final TutorRepository tutorRepository;

    public TutorController(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @GetMapping("/tutor/all")
    String getAllTutors() throws JsonProcessingException {
        return convertToJson(tutorRepository.findAll());
    }

    @GetMapping("/tutor/remove/{id}")
    String removeTutorById(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Tutor> tutorOptional = tutorRepository.findById(id);

        if (!tutorOptional.isPresent())
            return convertToJson("Invalid tutor id");

        tutorRepository.delete(tutorOptional.get());
        return convertToJson(tutorOptional.get());
    }

    @GetMapping("/tutor/getstudentsbysex/{id}")
    String getTutorMaleStudents(@PathVariable @NotNull @DecimalMin("1") Long id) throws JsonProcessingException {
        Optional<Tutor> tutorOptional = tutorRepository.findById(id);

        if (!tutorOptional.isPresent())
            return convertToJson("Invalid tutor id");

        List<Student> studentsMaleList = getTeacherGroups(tutorOptional.get()).stream()
                .map(Group::getStudents)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(student -> Objects.nonNull(student.getSex())
                        && StringUtils.equalsIgnoreCase(student.getSex(), "Male"))
                .collect(Collectors.toList());

        return convertToJson(studentsMaleList);
    }

    private List<Group> getTeacherGroups(Tutor tutor) {
        try {
            return tutor.getSubject().getGroups();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }
}
