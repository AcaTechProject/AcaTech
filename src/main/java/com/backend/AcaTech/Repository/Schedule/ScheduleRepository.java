package com.backend.AcaTech.Repository.Schedule;

import com.backend.AcaTech.Domain.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStartDateBetweenAndSchEdu(LocalDateTime startDate, LocalDateTime endDate, boolean schEdu);

    List<Schedule> findByStartDateAndSchEdu(LocalDate startDate, boolean schEdu);

    List<Schedule> findByStartDateAndSchEdu(LocalDateTime localDateTime, boolean schEdu);

    List<Schedule> findBySchIdIn(List<Long> schIds);
}