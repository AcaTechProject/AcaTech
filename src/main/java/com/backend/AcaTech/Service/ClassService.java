package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.UserClass;
import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Repository.Class.UserClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassService {

    private final UserClassRepository userClassRepository;
    public UserClass entity;
//
//    @Transactional
//    public List<BoardListResponseDto> searchAllDesc() {
//        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
//                .map(BoardListResponseDto::new)
//                .collect(Collectors.toList());
//    }

//    @Transactional
////    public ClassListResponseDto searchById(Long id) {
////        UserClass userClass = userClassRepository.findById(id).orElseThrow(()
////                -> new IllegalArgumentException("해당 과목이 없습니다."));
////        return new ClassListResponseDto(userClass);
////    }

    // 해당 유저 담당 수업 조회
    @Transactional
    public List<ClassListResponseDto> searchById(Long id) {
        return userClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(ClassListResponseDto::new)
                .collect(Collectors.toList());
    }
}
