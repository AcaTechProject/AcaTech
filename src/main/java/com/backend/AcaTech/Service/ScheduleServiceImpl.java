package com.backend.AcaTech.Service;

import com.backend.AcaTech.Dto.Schedule.ScheduleDto;
import com.backend.AcaTech.Repository.Schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new ScheduleDto(
                        schedule.getSchId(),
                        schedule.getUserId(),
                        schedule.getStartDate().toString(), // 예시로 Date 객체를 String으로 변환했습니다. 필요에 따라 수정하세요.
                        schedule.getEndDate().toString(),
                        schedule.getSchTitle(),
                        schedule.getSchContent(),
                        schedule.isSchEdu(),
                        schedule.isSchCons()
                ))
                .collect(Collectors.toList());
    }
}