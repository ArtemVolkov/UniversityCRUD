package com.volkov.UniversityCRUD.Service;

import com.volkov.UniversityCRUD.Util.DTOConverter;
import com.volkov.UniversityCRUD.model.*;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.model.dto.TutorDTO;
import com.volkov.UniversityCRUD.repository.TutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = criteriaBuilder.createQuery();
        Root<Student> from = query.from(Student.class);
        Join<Student, Group> join1 = from.join("group");
        Join<Group, Subject> join2 = join1.join("subjects");
        Join<Subject, Tutor> lastJoin = join2.join("tutor");
        query.select(from)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(lastJoin.get("id"), id),
                        criteriaBuilder.equal(from.get("sex"), "Male")
                        )
                );

        List<Object> resultList = entityManager.createQuery(query).getResultList();
        return resultList.stream()
                .map(o -> (Student) o)
                .map(DTOConverter::convertStudentToDTO)
                .collect(Collectors.toList());

    }
}
