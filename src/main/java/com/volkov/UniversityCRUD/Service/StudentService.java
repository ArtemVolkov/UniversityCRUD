package com.volkov.UniversityCRUD.Service;

import com.volkov.UniversityCRUD.Util.DTOConverter;
import com.volkov.UniversityCRUD.model.ReturnMessage;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentUpdateDTO> findAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(DTOConverter::convertStudentToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentUpdateDTO> findById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return Optional.of(DTOConverter.convertStudentToDTO(studentOptional.orElse(new Student())));
    }

    public ReturnMessage deleteStudentById(Long id) {
        ReturnMessage.ReturnMessageBuilder builder = ReturnMessage.builder();
        try {

            studentRepository.deleteById(id);
            return builder
                    .success(true)
                    .message("Student with id " + id + " has been removed!")
                    .build();

        } catch (EmptyResultDataAccessException e) {
            logger.error("Error in deleteTeacherById method! Id= " + id);
            return builder.
                    success(false)
                    .message(e)
                    .build();
        }

    }

    public long getStudentSubjectCount(Long id) {
        return studentRepository.getStudentSubjectCount(id);
    }

    public List<String> getStudentTeacherNameList(Long id) {
        return studentRepository.getStudentTeacherNameList(id);
    }

    public double getStudentTeachersAverageAge(Long id) {
        return studentRepository.getStudentTeachersAverageAge(id);
    }
}
