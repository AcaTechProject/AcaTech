package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Dto.Class.ClassDetailResponseDto;
import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Dto.Class.ClassStudentListResponseDto;
import com.backend.AcaTech.Dto.Class.NewClassInfoResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceRequestDto;
import com.backend.AcaTech.Dto.Student.StudentListResponseDto;
import com.backend.AcaTech.Service.ClassService;
import com.backend.AcaTech.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ClassController {

    @Autowired
    private final ClassService classService;


    @Autowired
    private final StudentService studentService;


    @GetMapping("/user/class/{id}")
    public List<ClassListResponseDto> searchById(@PathVariable Long id) {
        List<ClassListResponseDto> classList = classService.searchById(id);

        // user_id가 id와 일치하는 수업 목록만 추려서 반환
        // 컨트롤러에서 필터를 사용하는게 맞는방법일지!
        return classList.stream()
                .filter(classDto -> classDto.getUser().equals(id))
                .collect(Collectors.toList());
    }


    // 과목별 출결 조회
//    @GetMapping("/user/{classId}")
//    public ClassDetailResponseDto getClassDetailsByClassId(@PathVariable Long classId) {
//        return classService.getClassDetailsByClassId(classId);
//    }
//


//
//    @GetMapping("/user/{classId}")
//    public List<StudentListResponseDto> findStudentsByClassId(@PathVariable Long classId) {
//        return studentService.findStudentsByClassId(classId);
//    }
//
//

    // 특정 수업을 듣는 학생 리스트 조회 (classId로)
    @GetMapping("/user/{classId}")
    public ResponseEntity<Map<String, Object>> getStudentsByClassId(@PathVariable Long classId) {
        Map<String, Object> students = classService.findByName(classId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }





    @PostMapping("/user/{classId}")
    public ResponseEntity<?> createAttendance(@PathVariable Long classId, @RequestBody StudentAttendanceRequestDto attendanceRequestDto) {
        studentService.createStudentAttendance(classId,
                attendanceRequestDto.getStId(),
                attendanceRequestDto.getAttO(),
                attendanceRequestDto.getAttLate(),
                attendanceRequestDto.getAttX(),
                attendanceRequestDto.getAttEtc(),
                attendanceRequestDto.getAttReason(),
                attendanceRequestDto.getAttDate(),
                attendanceRequestDto.getAttResult());
        return ResponseEntity.ok("Attendance created");
    }

}