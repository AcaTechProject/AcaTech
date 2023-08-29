package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Consulting.NewStudent;
import com.backend.AcaTech.Dto.NewConsultingDto.NewConsultingDto;
import com.backend.AcaTech.Repository.NewConsulting.NewConsultingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class NewConsultingService {

    private final NewConsultingRepository NewConsultingRepository;
    public NewStudent entity;


    @Transactional
    public List<NewConsultingDto> getAllConsultings() {
        return NewConsultingRepository.findAll().stream()
                .map(NewConsultingDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<NewConsultingDto> searchById(Long id) {
        return (List<NewConsultingDto>) NewConsultingRepository.findAllById(id).stream()
                .map(NewConsultingDto::new)
                .collect(Collectors.toList());

    }



}