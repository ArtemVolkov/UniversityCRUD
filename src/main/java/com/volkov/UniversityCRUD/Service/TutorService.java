package com.volkov.UniversityCRUD.Service;

import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.repository.TutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private Logger Logger = LoggerFactory.getLogger(TutorService.class);

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> findAllTeachers() {
        return tutorRepository.findAll();
    }

    public Optional<Tutor> findTutorById(Long id) {
        return tutorRepository.findById(id);
    }

    public void deleteTeacherById(Long id) {
        try {
            tutorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            Logger.error("Error in deleteTeacherById method! Id= " + id);
        }
    }

    public List<Student> findTeacherMaleStudent(Long id) {
        return tutorRepository.findTutorStudentsBySex(id, "Male");
    }
}
