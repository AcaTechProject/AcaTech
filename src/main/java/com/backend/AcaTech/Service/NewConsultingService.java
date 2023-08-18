package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Consulting.NewStudent;
import com.backend.AcaTech.Dto.NewConsultingDto.newConsultingDto;
import com.backend.AcaTech.Repository.newConsulting.NewConsultingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewConsultingService {

    private final NewConsultingRepository newConsultingRepository;
    public NewStudent entity;

    @Transactional
    public List<newConsultingDto> searchById(Long id) {
        return (List<newConsultingDto>) newConsultingRepository.findAllById(id).stream()
                .map(newConsultingDto::new)
                .collect(Collectors.toList());

    }



}