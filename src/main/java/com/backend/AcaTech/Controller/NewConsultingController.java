package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.NewConsultingDto.NewConsultingDto;
import com.backend.AcaTech.Service.NewConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequiredArgsConstructor
//@RequiredArgsConstructor
@RestController
public class NewConsultingController {

    /*
    *	"st_id" : 88
		"st_name" : "김민지",
		"st_school" : "oo초등학교",
		"st_subject" : "국어"
    *
    * */

    @Autowired
    private final NewConsultingService newConsultingService;

    public NewConsultingController(NewConsultingService newConsultingService) {
        this.newConsultingService = newConsultingService;
    }


    // 전체 목록 조회
    @GetMapping("/newconsulting")
    public List<NewConsultingDto> getAllConsultings() {
        return newConsultingService.getAllConsultings();
    }


    //새로운 상담내용 조회
    @GetMapping(value = "/newconsulting/{id}")
    public List<NewConsultingDto> searchById(@PathVariable Long id) {
        return newConsultingService.searchById(id);
    }


}