package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Service.ConsultingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ConsultingController {

    private final ConsultingService consultingService;

    @PostMapping("/student/{id}/consulting")
    public Long create(@PathVariable Long id, @RequestBody ConsultingCreateRequestDto requestDto) {
        return consultingService.create(id, requestDto);
    }
}
