package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.File.FileDetail;
import com.backend.AcaTech.Service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UploadController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileDetail> uploadFile(
            @RequestPart("file") MultipartFile multipartFile) {
        FileDetail fileDetail = fileUploadService.save(multipartFile);
        return ResponseEntity.ok(fileDetail);
    }
}