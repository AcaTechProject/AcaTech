package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Consulting.NewStudent;
import com.backend.AcaTech.Dto.NewConsultingDto.NewConsultingDto;
import com.backend.AcaTech.Repository.NewConsulting.NewConsultingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RequiredArgsConstructor
@Service
public class NewConsultingService {

    private final NewConsultingRepository NewConsultingRepository;
    public NewStudent entity;


    @Transactional
    public List<NewConsultingDto> getAllConsultings() {

        List<NewStudent> allConsultings = NewConsultingRepository.findAll();

        int totalCount = allConsultings.size();

        return IntStream.range(0, totalCount)
                .mapToObj(index -> {
                    NewConsultingDto dto = new NewConsultingDto(allConsultings.get(index));
                    dto.setNum(totalCount - index);  // 내림차순 넘버링
                    return dto;
                })
                .collect(Collectors.toList());


//        return NewConsultingRepository.findAll().stream()
//                .map(NewConsultingDto::new)
//                .collect(Collectors.toList());
    }

    @Transactional
    public List<NewConsultingDto> searchById(Long id) {
        return (List<NewConsultingDto>) NewConsultingRepository.findAllById(id).stream()
                .map(NewConsultingDto::new)
                .collect(Collectors.toList());

    }

    // 신규 상담 등록
    @Transactional
    public Long createConsulting(NewConsultingDto newConsultingDto) {
        NewStudent newStudent = new NewStudent();
        newStudent.setNew_name(newConsultingDto.getSt_name());
        newStudent.setNew_gender(newConsultingDto.getSt_gender());
        newStudent.setNew_birth(newConsultingDto.getSt_birth());
        newStudent.setNew_school(newConsultingDto.getSt_school());
        newStudent.setNew_grade(newConsultingDto.getSt_grade());
        newStudent.setNew_phone(newConsultingDto.getSt_phone());
        newStudent.setNew_pa_phone(newConsultingDto.getSt_pa_phone());
        newStudent.setNew_class(newConsultingDto.getSt_class());
        newStudent.setNew_content(newConsultingDto.getSt_content());
        newStudent.setNew_etc(newConsultingDto.getSt_etc());

        NewConsultingRepository.save(newStudent);
        return newStudent.getId();
    }



}