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
        validateEmptyStudentDTOValues(student, studentDTO);
        modelMapper.map(studentDTO, student);
        return student;
    }

    public static Group updateGroupByDTO(GroupDTO groupDTO, Group group) {
        validateEmptyGroupDTOValues(groupDTO, group);
        modelMapper.map(groupDTO, group);
        return group;
    }

    /**
     * Checks every field in studentDTO if this field is empty but not null,
     * it`ll set to null and set to null in student field;
     */
    private static void validateEmptyStudentDTOValues(Student student, StudentUpdateDTO studentUpdateDTO) {
        if (studentUpdateDTO.getFullName() != null && studentUpdateDTO.getFullName().isEmpty()) {
            student.setFullName(null);
            studentUpdateDTO.setFullName(null);
        }
        if (studentUpdateDTO.getSex() != null && studentUpdateDTO.getSex().isEmpty()) {
            student.setSex(null);
            studentUpdateDTO.setSex(null);
        }
        if (studentUpdateDTO.getPhone() != null && studentUpdateDTO.getPhone().isEmpty()) {
            student.setPhone(null);
            studentUpdateDTO.setPhone(null);
        }
        if (studentUpdateDTO.getGroup() != null) {
            if (student.getGroup() == null) {
                student.setGroup(modelMapper.map(studentUpdateDTO.getGroup(), Group.class));
            }
            GroupDTO groupDTO = studentUpdateDTO.getGroup();
            validateEmptyGroupDTOValues(groupDTO, student.getGroup());
        }
        validateNullStudDTO(studentUpdateDTO, student);
    }

    /**
     * Checks every field in GroupDTO if this field is empty but not null,
     * it`ll set to null and set to null in group field;
     */
    private static void validateEmptyGroupDTOValues(GroupDTO groupDTO, Group group) {
        if (groupDTO.getGroupCode() != null && groupDTO.getGroupCode().isEmpty()) {
            groupDTO.setGroupCode(null);
            group.setGroupCode(null);
        }
        if (groupDTO.getGroupName() != null && groupDTO.getGroupName().isEmpty()) {
            groupDTO.setGroupName(null);
            group.setGroupName(null);
        }
        validateNullGroupDTO(groupDTO, group);
    }

    /**
     * Method will set value from student if
     * studentDTO appropriate field is null
     */
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
        if (studentDTO.getGroup() == null && student.getGroup() != null) {
            studentDTO.setGroup(convertGroupToDTO(student.getGroup()));
        }
        if (studentDTO.getSex() == null) {
            studentDTO.setSex(student.getSex());
        }
    }

    /**
     * Method will set values from group if
     * groupDTO appropriate field is null
     */
    private static void validateNullGroupDTO(GroupDTO groupDTO, Group group) {
        if (groupDTO.getGroupName() == null) {
            groupDTO.setGroupName(group.getGroupName());
        }
        if (groupDTO.getGroupCode() == null) {
            groupDTO.setGroupCode(group.getGroupCode());
        }
    }
}
