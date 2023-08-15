package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentAttendance;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceTotalResponseDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import com.backend.AcaTech.Dto.Student.StudentUpdateRequestDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Consulting.ConsultingRepository;
import com.backend.AcaTech.Repository.Student.StudentAttendanceRepository;
import com.backend.AcaTech.Repository.Student.StudentClassRepository;
import com.backend.AcaTech.Repository.Student.StudentFamilyRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    private final StudentFamilyRepository studentFamilyRepository;


    private final StudentClassRepository studentClassRepository;
    private Student entity;

    private final StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    // 왜썼더라
    public StudentService(StudentFamilyRepository studentFamilyRepository, StudentClassRepository studentClassRepository, StudentRepository studentRepository, StudentAttendanceRepository studentAttendanceRepository) {
        this.studentFamilyRepository = studentFamilyRepository;
        this.studentClassRepository = studentClassRepository;
        this.studentRepository = studentRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
    }

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
                .first_date(requestDto.getFirst_date())

                .st_write(requestDto.getSt_write())

                .st_update_write(requestDto.getSt_update_write())
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

        List<StudentFamily> familyInfos = studentFamilyRepository.findByStudent(student);
        List<StudentClass> classInfos = studentClassRepository.findByStudent(student);
        return new StudentResponseDto(student, familyInfos, classInfos);
    }

    // 학생 정보 수정
    @Transactional
    public void updateStudent(Long id, StudentUpdateRequestDto requestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        student.setName(requestDto.getName());
        student.setGender(requestDto.getGender());
        student.setBirth(requestDto.getBirth());
        student.setSchool(requestDto.getSchool());
        student.setGrade(requestDto.getGrade());
        student.setPhone(requestDto.getPhone());
        student.setEtc(requestDto.getEtc());
        student.setImage(requestDto.getImage());
        student.setTeacher(requestDto.getTeacher());
        student.setParentPhone(requestDto.getParentPhone());
        student.setUpdate_date(LocalDate.now());
        student.setSt_write(requestDto.getSt_write());
        student.setSt_update_write(requestDto.getSt_update_write());

        // 학생 정보 수정 후 저장
        studentRepository.save(student);

        // 가족 정보 업데이트
        if (requestDto.getFamilyInfos() != null) {
            // 기존 가족 정보 삭제
            studentFamilyRepository.deleteByStudent(student);

            // 새로운 가족 정보 추가
            for (StudentCreateRequestDto.FamilyInfo familyInfo : requestDto.getFamilyInfos()) {
                StudentFamily family = StudentFamily.builder()
                        .student(student)
                        .fa_name(familyInfo.getFa_name())
                        .fa_memo(familyInfo.getFa_memo())
                        .build();
                studentFamilyRepository.save(family);
            }
        }

        // 수강 정보 업데이트
        if (requestDto.getClassInfos() != null) {
            // 기존 수강 정보 삭제
            studentClassRepository.deleteByStudent(student);

            // 새로운 수강 정보 추가
            for (StudentCreateRequestDto.ClassInfo classInfo : requestDto.getClassInfos()) {
                StudentClass studentClass = StudentClass.builder()
                        .student(student)
                        .class_name(classInfo.getClass_name())
                        .build();
                studentClassRepository.save(studentClass);
            }
        }
    }



    // 학생 정보 삭제
    @Transactional
    public void delete(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        studentRepository.delete(student);
    }

    // 학생 출석 리스트
    @Transactional
    public List<StudentAttendanceListResponseDto> searchAllAttendanceList(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        List<StudentAttendance> attendances = studentAttendanceRepository.findByStudent(student);

        return attendances.stream()
                .map(StudentAttendanceListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 학생 출석 통계
    public StudentAttendanceTotalResponseDto getAttendanceStatistics(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<StudentAttendance> attendances = studentAttendanceRepository.findByStudent(student);

        long totalO = attendances.stream().filter(a -> "출석".equals(a.getAtt_result())).count();
        long totalLate = attendances.stream().filter(a -> "지각".equals(a.getAtt_result())).count();
        long totalX = attendances.stream().filter(a -> "결석".equals(a.getAtt_result())).count();
        long totalEtc = attendances.stream().filter(a -> "기타".equals(a.getAtt_result())).count();

        long allAttendance = totalO + totalLate + totalX + totalEtc;

        StudentAttendanceTotalResponseDto responseDto = new StudentAttendanceTotalResponseDto();
        responseDto.setStudentId(studentId);
        responseDto.setAllAttendance(allAttendance);
        responseDto.setTotalO(totalO);
        responseDto.setTotalLate(totalLate);
        responseDto.setTotalX(totalX);
        responseDto.setTotalEtc(totalEtc);

        return responseDto;
    }


}