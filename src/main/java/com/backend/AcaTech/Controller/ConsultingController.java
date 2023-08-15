package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingListResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingUpdateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Service.ConsultingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ConsultingController {

    private final ConsultingService consultingService;


    @Autowired
    public ConsultingController(ConsultingService consultingService) {
        this.consultingService = consultingService;
    }

    // 해당 학생 상담 등록
    @PostMapping("/student/{id}/consulting")
    public Long create(@PathVariable Long id, @RequestBody ConsultingCreateRequestDto requestDto) {
        return consultingService.create(id, requestDto);
    }

    // 해당 학생 상담 전체 조회
    @GetMapping("/student/{studentId}/consulting")
    public List<ConsultingListResponseDto> getConsultingList(@PathVariable Long studentId) {
        return consultingService.getConsultingList(studentId);
    }

    @GetMapping("/student/{studentId}/consulting/{conId}")
    public ResponseEntity<ConsultingResponseDto> getConsultingDetail(
            @PathVariable Long studentId,
            @PathVariable Long conId) {
        ConsultingResponseDto consultingResponse = consultingService.getConsultingDetails(studentId, conId);

        if (consultingResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(consultingResponse);
    }

    // 다중 삭제
    @DeleteMapping("/student/consulting/delete-multiple")
    public void deleteMultipleConsultings(@RequestBody List<Long> ids) {
        consultingService.deleteMultiple(ids);
    }

    // 수정
    @PutMapping("/student/consulting/{conId}")
    public Long update(@PathVariable Long conId, @RequestBody ConsultingUpdateRequestDto requestDto) {
        return consultingService.update(conId, requestDto);
    }


}
