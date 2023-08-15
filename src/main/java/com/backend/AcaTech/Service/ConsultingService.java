package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Consulting.ConsultingRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsultingService {

    private final ConsultingRepository consultingRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
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
}
