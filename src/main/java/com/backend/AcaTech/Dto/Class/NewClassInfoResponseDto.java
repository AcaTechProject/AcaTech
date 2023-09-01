package com.backend.AcaTech.Dto.Class;

import com.backend.AcaTech.Dto.Student.StudentListResponseDto;
import lombok.Data;

import java.util.*;

@Data
public class NewClassInfoResponseDto {
    private String className;
    private List<StudentListResponseDto> students;
}
