package com.volkov.UniversityCRUD.Service;

import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
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

    private Logger Logger = LoggerFactory.getLogger(TutorService.class);

    private final EntityManager entityManager;
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository, EntityManager entityManager) {
        this.tutorRepository = tutorRepository;
        this.entityManager = entityManager;
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

    public List<Student> findTeacherMaleStudent2(Long id) {
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
                .collect(Collectors.toList());

    }
}
