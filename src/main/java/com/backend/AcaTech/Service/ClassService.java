package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.UserClass;
import com.backend.AcaTech.Dto.Class.ClassDetailResponseDto;
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

    @Transactional
    public List<ClassListResponseDto> searchById(Long id) {
        return userClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(ClassListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public ClassDetailResponseDto getClassDetailsByClassId(Long classId) {
        // UserClassRepository를 사용해 해당 classId로 UserClass를 찾습니다.
        UserClass userClass = userClassRepository.findById(classId).orElseThrow(()
                -> new IllegalArgumentException("해당 수업 없음"));

        // UserClass 엔터티를 DTO에 전달하여 반환합니다.
        return new ClassDetailResponseDto(userClass);
    }

    private String parseCourseName(String className) {
        int spaceIndex = className.indexOf(" ");
        if (spaceIndex != -1) {
            return className.substring(0, spaceIndex);
        }
        return className;  // 공백이 없는 경우 전체 문자열 반환
    }







}
