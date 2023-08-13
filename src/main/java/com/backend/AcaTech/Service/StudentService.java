package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import com.backend.AcaTech.Repository.Student.StudentClassRepository;
import com.backend.AcaTech.Repository.Student.StudentFamilyRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentFamilyRepository studentFamilyRepository;

    private final StudentClassRepository studentClassRepository;
    private Student entity;

    // 신규 학생 추가
    @Transactional
    public Long createStudent(StudentCreateRequestDto requestDto) {
        Student student = Student.builder()
                .name(requestDto.getName())
                .gender(requestDto.getGender())
                .birth(requestDto.getBirth())
                .school(requestDto.getSchool())
                .grade(requestDto.getGrade())
                .phone(requestDto.getPhone())
                .etc(requestDto.getEtc())
                .image(requestDto.getImage())
                .teacher(requestDto.getTeacher())
                .parentPhone(requestDto.getParentPhone())
                .build();

        Student savedStudent = studentRepository.save(student);

        if (requestDto.getFamilyInfos() != null) {
            for (StudentCreateRequestDto.FamilyInfo familyInfo : requestDto.getFamilyInfos()) {
                StudentFamily family = StudentFamily.builder()
                        .student(savedStudent)
                        .fa_name(familyInfo.getFa_name())
                        .fa_memo(familyInfo.getFa_memo())
                        .build();
                studentFamilyRepository.save(family);
            }
        }

        if (requestDto.getClassInfos() != null) {
            for (StudentCreateRequestDto.ClassInfo classInfo : requestDto.getClassInfos()) {
                StudentClass studentClass = StudentClass.builder()
                        .student(savedStudent)
                        .class_name(classInfo.getClass_name())
                        .build();
                studentClassRepository.save(studentClass);
            }
        }

        return savedStudent.getId();
    }

    // 학생 상세 조회
    @Transactional
    public StudentResponseDto searchById(Long id) {
        Student student = studentRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("해당 학생이 존재하지 않습니다."));
        return new StudentResponseDto(student);
    }
}
