package com.backend.AcaTech.Repository.Schedule;

import com.backend.AcaTech.Domain.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}