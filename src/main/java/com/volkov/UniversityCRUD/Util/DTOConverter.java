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


}
