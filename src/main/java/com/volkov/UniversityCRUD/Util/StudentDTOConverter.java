package com.volkov.UniversityCRUD.Util;

import com.volkov.UniversityCRUD.model.Student;
import com.volkov.UniversityCRUD.model.dto.StudentUpdateDTO;
import org.modelmapper.ModelMapper;

public class StudentDTOConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static StudentUpdateDTO convertStudentToDTO(Student student) {
        return modelMapper.map(student, StudentUpdateDTO.class);
    }


}
