package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingListResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingUpdateRequestDto;
import com.backend.AcaTech.Dto.Response.ResponseMessage;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentUpdateRequestDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Consulting.ConsultingRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResponseMessage create(Long studentId, ConsultingCreateRequestDto requestDto) {
        try {
            validateInput(requestDto, studentId);

            User user = userRepository.findById(requestDto.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

            Consulting consulting = requestDto.toEntity();
            consulting.setUser(user);
            consulting.setStudent(student);

            Consulting savedConsulting = consultingRepository.save(consulting);

            // 저장된 값들을 반환
            ConsultingResponseDto responseDto = new ConsultingResponseDto(savedConsulting);
            return new ResponseMessage<>(true, "성공", responseDto);
        } catch (IllegalArgumentException ex) {
            return new ResponseMessage<>(false, ex.getMessage(), null);
        } catch (Exception e) {
            return new ResponseMessage<>(false, "서버 오류: " + e.getMessage(), null);
        }
    }



    private void validateInput(ConsultingCreateRequestDto requestDto, Long studentId) {
        if (requestDto == null) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }

        if (requestDto.getUser() == null || requestDto.getUser().getId() == null) {
            throw new IllegalArgumentException("유저 정보가 누락되었습니다.");
        }

        if (studentId == null) {
            throw new IllegalArgumentException("학생 아이디가 누락되었습니다.");
        }

    }



    public List<ConsultingListResponseDto> getConsultingList(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<Consulting> consulting = consultingRepository.findByStudent(student);

        List<ConsultingListResponseDto> responseDtoList = new ArrayList<>();
        for (int i = 0; i < consulting.size(); i++) {
            Consulting consultingItem = consulting.get(i);
            ConsultingListResponseDto responseDto = new ConsultingListResponseDto(consultingItem);
            responseDto.setNo((long) (i + 1)); // No를 추가
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    //상세 조회
    @Transactional
    public ConsultingResponseDto getConsultingDetails(Long studentId, Long conId) {
        Consulting consulting = consultingRepository.findByIdAndStudentId(conId, studentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 학생의 상담 내용이 존재하지 않습니다."));

        return new ConsultingResponseDto(consulting);
    }


    // 삭제
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        List<Consulting> consultings = consultingRepository.findByIdIn(ids);
        consultingRepository.deleteAll(consultings);
    }



    @Transactional
    public Long update(Long id, ConsultingUpdateRequestDto requestDto) {
        Consulting consulting = consultingRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        consulting.update(requestDto.getCon_who(),
                requestDto.getCon_content(), requestDto.getCon_class());

        return id;
    }
}
