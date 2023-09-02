package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ClassController {

    @Autowired
    private final ClassService classService;




    @GetMapping("/user/class/{id}")
    public List<ClassListResponseDto> searchById(@PathVariable Long id) {
        List<ClassListResponseDto> classList = classService.searchById(id);

        // user_id가 id와 일치하는 수업 목록만 추려서 반환
        // 컨트롤러에서 필터를 사용하는게 맞는방법일지!
        return classList.stream()
                .filter(classDto -> classDto.getUser().equals(id))
                .collect(Collectors.toList());
    }
}
