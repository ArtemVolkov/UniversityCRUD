package com.volkov.UniversityCRUD.Util;

import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.model.dto.GroupDTO;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.model.dto.SubjectDTO;
import com.volkov.UniversityCRUD.model.dto.TutorDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StudentDTOConverterTest extends Assert {

    private Student student;
    private Group group;
    private StudentUpdateDTO expectedStudentDTO = new StudentUpdateDTO();
    private GroupDTO expectedGroupDTO = new GroupDTO();

    @Before
    public void initializeNewStudentAndGroup() {
        group = new Group();
        group.setId(5L);
        group.setGroupName("aaa");
        group.setGroupCode("165");

        student = new Student();
        student.setGroup(group);
        student.setId(5L);
        student.setMark(5d);
        student.setAge(5);
        student.setPhone("555");
        student.setFullName("Fullname");
        student.setSex("Male");

        expectedGroupDTO.setGroupCode(group.getGroupCode());
        expectedGroupDTO.setGroupName(group.getGroupName());

        expectedStudentDTO.setGroup(expectedGroupDTO);
        expectedStudentDTO.setId(student.getId());
        expectedStudentDTO.setMark(student.getMark());
        expectedStudentDTO.setAge(student.getAge());
        expectedStudentDTO.setPhone(student.getPhone());
        expectedStudentDTO.setFullName(student.getFullName());
        expectedStudentDTO.setSex(student.getSex());
    }

    @Test
    public void testDTOStudentAndGroupConverting() {
        StudentUpdateDTO studentDTO = DTOConverter.convertStudentToDTO(student);
        GroupDTO groupDTO = DTOConverter.convertGroupToDTO(group);

        assertEquals(expectedGroupDTO, groupDTO);
        assertEquals(expectedStudentDTO, studentDTO);
    }

    private Tutor tutor;
    private Subject subject;
    private SubjectDTO expectedSubjectDTO = new SubjectDTO();
    private TutorDTO expectedTutorDTO = new TutorDTO();

    @Before
    public void initializeNewTutorAndSubject() {
        subject = new Subject();
        subject.setId(5L);
        subject.setSubjectTitle("Math");
        subject.setSubjectCode("0525");

        tutor = new Tutor();
        tutor.setId(5L);
        tutor.setFullName("A.Berdugin");
        tutor.setAge(30);
        tutor.setPhone("06578");
        tutor.setSubject(subject);

        expectedSubjectDTO.setSubjectTitle(subject.getSubjectTitle());
        expectedSubjectDTO.setSubjectCode(subject.getSubjectCode());

        expectedTutorDTO.setId(tutor.getId());
        expectedTutorDTO.setFullName(tutor.getFullName());
        expectedTutorDTO.setAge(tutor.getAge());
        expectedTutorDTO.setPhone(tutor.getPhone());
        expectedTutorDTO.setSubject(expectedSubjectDTO);
    }


    public void testDTOTutorAndSubjectConverting() {
        TutorDTO tutorDTO = DTOConverter.convertTutorToDTO(tutor);
        SubjectDTO subjectDTO = DTOConverter.convertSubjectToDTO(subject);

        assertEquals(expectedSubjectDTO, subjectDTO);
        assertEquals(expectedTutorDTO, tutorDTO);
    }

}
