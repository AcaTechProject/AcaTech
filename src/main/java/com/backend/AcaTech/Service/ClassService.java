package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.UserClass;
import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Repository.UserClassRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public List<ClassListResponseDto> searchById(Long id) {
        return userClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(ClassListResponseDto::new)
                .collect(Collectors.toList());
    }
}
