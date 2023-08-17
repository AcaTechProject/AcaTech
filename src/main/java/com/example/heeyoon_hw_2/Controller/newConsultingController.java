package com.example.heeyoon_hw_2.Controller;

import com.example.heeyoon_hw_2.Dto.newConsultingDto;
import com.example.heeyoon_hw_2.Service.newConsultingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//@RequiredArgsConstructor
@RestController
public class newConsultingController {

    /*
    *	"st_id" : 88
		"st_name" : "김민지",
		"st_school" : "oo초등학교",
		"st_subject" : "국어"
    *
    * */

    @Autowired
    private final newConsultingService newConsultingService;

    public newConsultingController(com.example.heeyoon_hw_2.Service.newConsultingService newConsultingService) {
        this.newConsultingService = newConsultingService;
    }

    //새로운 상담내용 조회

    @GetMapping(value="/newconsulting")
    public List<newConsultingDto> searchById(@PathVariable Long id) {
        return newConsultingService.searchById(id);
    }




}
