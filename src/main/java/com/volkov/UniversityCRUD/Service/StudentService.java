package com.volkov.UniversityCRUD.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.volkov.UniversityCRUD.model.*;
import com.volkov.UniversityCRUD.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final EntityManager entityManager;
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
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
