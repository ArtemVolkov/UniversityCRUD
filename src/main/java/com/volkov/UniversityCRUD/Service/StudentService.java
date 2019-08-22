package com.volkov.UniversityCRUD.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.volkov.UniversityCRUD.Util.DTOConverter;
import com.volkov.UniversityCRUD.model.*;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private Logger Logger = LoggerFactory.getLogger(StudentService.class);

    private final EntityManager entityManager;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
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

    public void deleteStudentById(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            Logger.error("Error in deleteTeacherById method! Id= " + id);
        }

    }

    public long getStudentSubjectCount(Long id) {
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        QSubject subject = QSubject.subject;
        jpaQuery.from(subject)
                .innerJoin(subject.groups, QGroup.group)
                .innerJoin(QGroup.group.students, QStudent.student)
                .where(QStudent.student.id.eq(id));

        return jpaQuery.fetchCount();

    }

    public List<String> getStudentTeacherNameList(Long id) {
        JPAQuery jpaQuery = new JPAQuery<>(entityManager);
        QTutor tutor = QTutor.tutor;
        jpaQuery.select(tutor.fullName).from(tutor)
                .innerJoin(tutor.subject, QSubject.subject)
                .innerJoin(QSubject.subject.groups, QGroup.group)
                .innerJoin(QGroup.group.students, QStudent.student)
                .where(QStudent.student.id.eq(id));

        return ((List<String>) jpaQuery.fetch());

    }

    public double getStudentTeachersAverageAge(Long id) {
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        QTutor tutor = QTutor.tutor;
        jpaQuery
                .select(tutor.age.avg())
                .from(tutor)
                .innerJoin(tutor.subject, QSubject.subject)
                .innerJoin(QSubject.subject.groups, QGroup.group)
                .innerJoin(QGroup.group.students, QStudent.student)
                .where(QStudent.student.id.eq(id));

        Object o = jpaQuery.fetchFirst();
        return Optional.ofNullable((Double) o).orElse(0d);


    }
}
