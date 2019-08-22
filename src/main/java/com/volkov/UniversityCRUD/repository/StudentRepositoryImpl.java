package com.volkov.UniversityCRUD.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.volkov.UniversityCRUD.model.QGroup;
import com.volkov.UniversityCRUD.model.QStudent;
import com.volkov.UniversityCRUD.model.QSubject;
import com.volkov.UniversityCRUD.model.QTutor;
import com.volkov.UniversityCRUD.repository.interfaces.StudentRepositoryCustomInterface;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepositoryCustomInterface {

    private final EntityManager entityManager;

    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public long getStudentSubjectCount(Long id) {
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        QSubject subject = QSubject.subject;
        jpaQuery.from(subject)
                .innerJoin(subject.groups, QGroup.group)
                .innerJoin(QGroup.group.students, QStudent.student)
                .where(QStudent.student.id.eq(id));

        return jpaQuery.fetchCount();
    }

    @Override
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

    @Override
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
