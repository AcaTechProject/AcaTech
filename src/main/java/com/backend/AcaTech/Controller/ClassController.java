package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ClassController {

    private final ClassService classService;

//    @GetMapping("/user/class/{id}")
//    public ClassListResponseDto searchById(@PathVariable Long id) {
//        return classService.searchById(id);
//    }

    @GetMapping("/user/class/{id}")
    public List<ClassListResponseDto> searchById(@PathVariable Long id) {
        // 만약 파라미터의 id 값과 classService의 searchById 리스트 중 user_id가 같은 경우만 출력
        List<ClassListResponseDto> classList = classService.searchById(id);

        // user_id가 id와 일치하는 수업 목록만 추려서 반환
        return classList.stream()
                .filter(classDto -> classDto.getUser().equals(id))
                .collect(Collectors.toList());
    }
}
