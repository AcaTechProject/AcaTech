package com.example.heeyoon_hw_2.Service;

import com.example.heeyoon_hw_2.Domain.newStudent;
import com.example.heeyoon_hw_2.Dto.newConsultingDto;
import com.example.heeyoon_hw_2.Repository.newConsultingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class newConsultingService {

    private final newConsultingRepository newConsultingRepository;
    public newStudent entity;

    @Transactional
    public List<newConsultingDto> searchById(Long id) {
        return (List<newConsultingDto>) newConsultingRepository.findAllById(id).stream()
                .map(newConsultingDto::new)
                .collect(Collectors.toList());

    }



}
