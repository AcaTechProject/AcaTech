package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Schedule.ScheduleDto;
import com.backend.AcaTech.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //일정 조회
    @GetMapping("/list")
    public Map<String, Object> getScheduleInfo() {
        return scheduleService.getScheduleInfo();
    }

    //일정 등록
    @PostMapping("/create")
    public ScheduleDto createSchedule(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.createSchedule(scheduleDto);
    }

    @DeleteMapping("/delete/multiple")
    public void deleteSchedules(@RequestBody List<Long> ids) {scheduleService.deleteSchedules(ids);}
}