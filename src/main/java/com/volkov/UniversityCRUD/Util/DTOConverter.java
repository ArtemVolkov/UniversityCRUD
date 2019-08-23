package com.volkov.UniversityCRUD.Util;

import com.volkov.UniversityCRUD.model.Group;
import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.Subject;
import com.volkov.UniversityCRUD.model.Tutor;
import com.volkov.UniversityCRUD.model.dto.GroupDTO;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import com.volkov.UniversityCRUD.model.dto.SubjectDTO;
import com.volkov.UniversityCRUD.model.dto.TutorDTO;
import org.modelmapper.ModelMapper;

public class DTOConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static StudentUpdateDTO convertStudentToDTO(Student student) {
        return modelMapper.map(student, StudentUpdateDTO.class);
    }

    public static GroupDTO convertGroupToDTO(Group group) {
        return modelMapper.map(group, GroupDTO.class);
    }

    public static TutorDTO convertTutorToDTO(Tutor tutor) {
        return modelMapper.map(tutor, TutorDTO.class);
    }

    public static SubjectDTO convertSubjectToDTO(Subject subject) {
        return modelMapper.map(subject, SubjectDTO.class);
    }

    public static Student updateStudentByDTO(StudentUpdateDTO studentDTO, Student student) {
        validateNullStudDTO(studentDTO, student);
        modelMapper.map(studentDTO, student);
        return student;
    }

    public static Group updateGroupByDTO(GroupDTO groupDTO, Group group) {
        validateNullGroupDTO(groupDTO, group);
        modelMapper.map(groupDTO, group);
        return group;
    }

    private static void validateNullStudDTO(StudentUpdateDTO studentDTO, Student student) {
        if (studentDTO.getId() == null) {
            studentDTO.setId(student.getId());
        }
        if (studentDTO.getFullName() == null) {
            studentDTO.setFullName(student.getFullName());
        }
        if (studentDTO.getAge() == null) {
            studentDTO.setAge(student.getAge());
        }
        if (studentDTO.getMark() == null) {
            studentDTO.setMark(student.getMark());
        }
        if (studentDTO.getPhone() == null) {
            studentDTO.setPhone(student.getPhone());
        }
        if (studentDTO.getGroup() == null) {
            studentDTO.setGroup(convertGroupToDTO(student.getGroup()));
        }
        if (studentDTO.getSex() == null) {
            studentDTO.setSex(student.getSex());
        }
    }

    private static void validateNullGroupDTO(GroupDTO groupDTO, Group group) {
        if (groupDTO.getGroupName() == null) {
            groupDTO.setGroupName(group.getGroupName());
        }
        if (groupDTO.getGroupCode() == null) {
            groupDTO.setGroupCode(group.getGroupCode());
        }
    }
}
