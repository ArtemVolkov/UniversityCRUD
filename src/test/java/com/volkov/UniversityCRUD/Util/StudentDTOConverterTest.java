package com.volkov.UniversityCRUD.Util;

import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.dto.GroupDTO;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StudentDTOConverterTest extends Assert {

    private Student student;
    private Group group;
    private StudentUpdateDTO expectedStudentDTO = new StudentUpdateDTO();
    private GroupDTO expectedGroupDTO = new GroupDTO();

    @Before
    public void creteNewStudent() {
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
    public void testDTOConverting() {
        StudentUpdateDTO studentDTO = DTOConverter.convertStudentToDTO(student);
        GroupDTO groupDTO = DTOConverter.convertGroupToDTO(group);

        assertEquals(expectedGroupDTO,groupDTO);
        assertEquals(expectedStudentDTO,studentDTO);
    }

}
