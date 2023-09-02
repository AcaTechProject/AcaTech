package com.backend.AcaTech.Service;

import com.backend.AcaTech.Dto.Schedule.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getAllSchedules();
}