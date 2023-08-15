package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingListResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingResponseDto;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Consulting.ConsultingRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsultingService {

    private final ConsultingRepository consultingRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
    // 왜썼더라
    public ConsultingService(ConsultingRepository consultingRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.consultingRepository = consultingRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }
    public Long create(Long studentId, ConsultingCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        Consulting consulting = requestDto.toEntity();
        consulting.setUser(user);
        consulting.setStudent(student);

        return consultingRepository.save(consulting).getId();
    }

    @Transactional
    public List<ConsultingListResponseDto> getConsultingList(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<Consulting> consulting = consultingRepository.findByStudent(student);

        return consulting.stream()
                .map(ConsultingListResponseDto::new)
                .collect(Collectors.toList());
    }

    //상세 조회
    @Transactional
    public ConsultingResponseDto getConsultingDetails(Long studentId, Long conId) {
        Consulting consulting = consultingRepository.findByIdAndStudentId(conId, studentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 학생의 상담 내용이 존재하지 않습니다."));

        return new ConsultingResponseDto(consulting);
    }


    // 삭제제
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        List<Consulting> consultings = consultingRepository.findByIdIn(ids);
        consultingRepository.deleteAll(consultings);
    }

}
