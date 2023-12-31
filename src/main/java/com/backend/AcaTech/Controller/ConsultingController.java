package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingListResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingResponseDto;
import com.backend.AcaTech.Dto.Consulting.ConsultingUpdateRequestDto;
import com.backend.AcaTech.Dto.Response.ResponseMessage;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Service.ConsultingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseMessage> create(@PathVariable Long id, @RequestBody ConsultingCreateRequestDto requestDto) {
        ResponseMessage responseMessage = consultingService.create(id, requestDto);
        return ResponseEntity.ok(responseMessage);
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
    public ResponseEntity<ResponseMessage<Void>> deleteMultipleConsultings(@RequestBody List<Long> ids) {
        ResponseMessage<Void> responseMessage = consultingService.deleteMultiple(ids);

        if (responseMessage.isSuccess()) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }


    // 수정
    @PutMapping("/student/consulting/{conId}")
    public Long update(@PathVariable Long conId, @RequestBody ConsultingUpdateRequestDto requestDto) {
        return consultingService.update(conId, requestDto);
    }


}
