package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Schedule.Schedule;
import com.backend.AcaTech.Dto.Schedule.ScheduleDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {
    Map<String, Object> getScheduleInfo();

    List<ScheduleDto> getAllSchedules();

    List<Schedule> findByStartDateBetweenAndSchEdu(LocalDateTime startDate, LocalDateTime endDate, boolean schEdu, boolean schCons);

    ScheduleDto createSchedule(ScheduleDto scheduleDto);

    void deleteSchedules(List<Long> schIds);
}