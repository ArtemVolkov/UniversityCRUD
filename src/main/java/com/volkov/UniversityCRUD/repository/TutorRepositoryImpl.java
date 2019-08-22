package com.volkov.UniversityCRUD.repository;

import com.volkov.UniversityCRUD.Util.DTOConverter;
import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.repository.interfaces.TutorRepositoryCustomInterface;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TutorRepositoryImpl implements TutorRepositoryCustomInterface {

    private final EntityManager entityManager;

    public TutorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
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
