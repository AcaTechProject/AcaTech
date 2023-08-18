package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.NewConsultingDto.newConsultingDto;
import com.backend.AcaTech.Service.NewConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private NewConsultingService newConsultingService;

    public NewConsultingController(NewConsultingService service) {
        this.newConsultingService = service;
    }

    //새로운 상담내용 상세 조회
    @GetMapping(value = "/newconsulting/{id}")
    public List<newConsultingDto> searchById(@PathVariable Long id) {
        return newConsultingService.searchById(id);
    }
}



