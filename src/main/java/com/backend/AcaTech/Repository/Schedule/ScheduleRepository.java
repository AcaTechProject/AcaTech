package com.backend.AcaTech.Repository.Schedule;

import com.backend.AcaTech.Domain.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findBySchIdIn(List<Long> schIds);

    List<Schedule> findByStartDateBetweenAndSchEduOrSchCons(
            LocalDateTime startDate, LocalDateTime endDate, boolean schEdu, boolean schCons);

    List<Schedule> findByStartDateBetweenAndSchEdu(LocalDateTime localDateTime, LocalDateTime localDateTime1, boolean schEdu);
}
