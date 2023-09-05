package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Class.*;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceRequestDto;
import com.backend.AcaTech.Service.ClassService;
import com.backend.AcaTech.Service.StudentService;
import jakarta.persistence.EntityNotFoundException;
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
    @GetMapping("/user/class2/{classId}")
    public ResponseEntity<Map<String, Object>> getStudentsByClassId(@PathVariable Long classId) {
        Map<String, Object> students = classService.findByName(classId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // 새로운 출결 등록
//    @PostMapping("/user/{classId}")

    @PostMapping("/user/class2/{classId}")
    public ResponseEntity<?> createAttendance(@PathVariable Long classId, @RequestBody List<StudentAttendanceRequestDto> attendanceRequestDtos) {
        for(StudentAttendanceRequestDto attendanceRequestDto : attendanceRequestDtos) {
            studentService.createStudentAttendance(classId,
                    attendanceRequestDto.getStId(),
                    attendanceRequestDto.getAttO(),
                    attendanceRequestDto.getAttLate(),
                    attendanceRequestDto.getAttX(),
                    attendanceRequestDto.getAttEtc(),
                    attendanceRequestDto.getAttReason(),
                    attendanceRequestDto.getAttDate(),
//                    java.time.LocalDate.now()
                    attendanceRequestDto.getAttResult());
        }
        return ResponseEntity.ok("Attendances created");
    }


    //지난 출결 내용 조회
    @GetMapping("/user/{classId}/prevclass")
    public ResponseEntity<List<PreviousAttendanceDto>> getPreviousAttendances(@PathVariable Long classId) {
        List<PreviousAttendanceDto> previousAttendances = classService.getPreviousAttendances(classId);
        return ResponseEntity.ok(previousAttendances);
    }


    //지난 출결 정보 수정
    @PatchMapping("/user/{classId}/prevclass")
    public ResponseEntity<String> updateMultipleAttendances(@PathVariable Long classId, @RequestBody AttendanceUpdateRequestDto requestDto) {
        try {
            if (!classId.equals(requestDto.getClassId())) {
                return new ResponseEntity<>("ClassId mismatch", HttpStatus.BAD_REQUEST);
            }

            classService.updateMultipleAttendances(requestDto);
            return new ResponseEntity<>("Attendances updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}