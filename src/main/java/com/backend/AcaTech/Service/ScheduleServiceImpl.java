package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Schedule.Schedule;
import com.backend.AcaTech.Dto.Schedule.ScheduleDto;
import com.backend.AcaTech.Repository.Schedule.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserService userService) {
        this.scheduleRepository = scheduleRepository;
        this.userService = userService;
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new ScheduleDto(
                        schedule.getSchId(),
                        schedule.getUser().getId(),
                        schedule.getStartDate().toString(),
                        schedule.getEndDate().toString(),
                        schedule.getSchTitle(),
                        schedule.getSchContent(),
                        schedule.isSchEdu(),
                        schedule.isSchCons()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Long userId = scheduleDto.getUser_id();
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Schedule schedule = new Schedule(
                    user,
                    LocalDateTime.parse(scheduleDto.getStart_date()),
                    LocalDateTime.parse(scheduleDto.getEnd_date()),
                    scheduleDto.getSch_title(),
                    scheduleDto.getSch_content(),
                    scheduleDto.isSch_edu(),
                    scheduleDto.isSch_cons()
            );

            Schedule savedSchedule = scheduleRepository.save(schedule);

            return new ScheduleDto(
                    savedSchedule.getSchId(),
                    savedSchedule.getUser().getId(),
                    savedSchedule.getStartDate().toString(),
                    savedSchedule.getEndDate().toString(),
                    savedSchedule.getSchTitle(),
                    savedSchedule.getSchContent(),
                    savedSchedule.isSchEdu(),
                    savedSchedule.isSchCons()
            );
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    @Override
    public List<Schedule> findByStartDateBetweenAndSchEdu(LocalDateTime startDate, LocalDateTime endDate, boolean schEdu) {
        return scheduleRepository.findByStartDateBetweenAndSchEdu(startDate, endDate, schEdu);
    }

    @Override
    public Map<String, Object> getScheduleInfo() {
        Map<String, Object> response = new HashMap<>();

        LocalDate currentDate = LocalDate.now();

        LocalDateTime startOfMonth = currentDate.atStartOfDay();
        LocalDateTime endOfMonth = currentDate.atStartOfDay().plusMonths(1).minusNanos(1);

        List<ScheduleDto> thisMonthSchedules = mapToDto(scheduleRepository.findByStartDateBetweenAndSchEdu(startOfMonth, endOfMonth, true));
        List<ScheduleDto> todayEduSchedules = mapToDto(scheduleRepository.findByStartDateAndSchEdu(currentDate.atStartOfDay(), true));
        List<ScheduleDto> todayConsSchedules = mapToDto(scheduleRepository.findByStartDateAndSchEdu(currentDate.atStartOfDay(), false));

        response.put("this_month_schedule", thisMonthSchedules);
        response.put("today_edu_schedule", todayEduSchedules);
        response.put("today_cons_schedule", todayConsSchedules);

        return response;
    }

    private List<ScheduleDto> mapToDto(List<Schedule> schedules) {
        return schedules.stream()
                .map(schedule -> new ScheduleDto(
                        schedule.getSchId(),
                        schedule.getUser().getId(),
                        schedule.getStartDate().toString(),
                        schedule.getEndDate().toString(),
                        schedule.getSchTitle(),
                        schedule.getSchContent(),
                        schedule.isSchEdu(),
                        schedule.isSchCons()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSchedules(List<Long> schIds) {
        List<Schedule> schedulesToDelete = scheduleRepository.findBySchIdIn(schIds);
        scheduleRepository.deleteAll(schedulesToDelete);
    }
}