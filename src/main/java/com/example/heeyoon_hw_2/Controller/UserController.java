package com.example.heeyoon_hw_2.Controller;


import com.example.heeyoon_hw_2.Dto.CourseWithStudentsDto;
import com.example.heeyoon_hw_2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/{classId}")
    public ResponseEntity<CourseWithStudentsDto> getStudentsByClassId(@PathVariable Long classId) {
        CourseWithStudentsDto courseWithStudentsDto = userService.getStudentByClassId(classId);
        return ResponseEntity.ok(courseWithStudentsDto);
    }

}
