package com.volkov.UniversityCRUD.Service;

import com.volkov.UniversityCRUD.Util.DTOConverter;
import com.volkov.UniversityCRUD.model.ReturnMessage;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.model.dto.TutorDTO;
import com.volkov.UniversityCRUD.repository.TutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorService {

    private Logger logger = LoggerFactory.getLogger(TutorService.class);

    private final EntityManager entityManager;
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository, EntityManager entityManager) {
        this.tutorRepository = tutorRepository;
        this.entityManager = entityManager;
    }

    public List<TutorDTO> findAllTeachers() {
        return tutorRepository.findAll().stream()
                .map(DTOConverter::convertTutorToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TutorDTO> findTutorById(Long id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        return Optional.ofNullable(DTOConverter.convertTutorToDTO(tutor.orElse(new Tutor())));
    }

    public ReturnMessage deleteTeacherById(Long id) {
        ReturnMessage.ReturnMessageBuilder builder = ReturnMessage.builder();
        try {
            tutorRepository.deleteById(id);
            return builder
                    .success(true)
                    .message("Tutor with id " + id + " has been removed!")
                    .build();
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error in deleteTeacherById method! Id= " + id);
            return builder
                    .success(false)
                    .message(e)
                    .build();
        }
    }

    public List<StudentUpdateDTO> findTeacherMaleStudent(Long id) {
        return tutorRepository.findTutorStudentsBySex(id, "Male").stream()
                .map(DTOConverter::convertStudentToDTO)
                .collect(Collectors.toList());
    }

    public List<StudentUpdateDTO> findTeacherMaleStudent2(Long id) {
        return tutorRepository.findTeacherMaleStudent2(id);
    }
}
