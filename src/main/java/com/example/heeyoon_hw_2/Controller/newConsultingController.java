package com.example.heeyoon_hw_2.Controller;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class newConsultingController {

    /*
    *	"st_id" : 88
		"st_name" : "김민지",
		"st_school" : "oo초등학교",
		"st_subject" : "국어"
    *
    * */
@GetMapping(value="/consulting")
    public List<list> getStudents(@PathVariable Long studentId) {


}






}
