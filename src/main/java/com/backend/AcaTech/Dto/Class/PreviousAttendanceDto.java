package com.backend.AcaTech.Dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PreviousAttendanceDto {
    private Long no;
    private LocalDate dateTime;
    private String className;
    private String attendanceInfo;

    public PreviousAttendanceDto() {
    }

    public PreviousAttendanceDto(Long no, LocalDate dateTime, String className, String attendanceInfo) {
        this.no = no;
        this.dateTime = dateTime;
        this.className = className;
        this.attendanceInfo = attendanceInfo;
    }

    public void addAttendanceInfo(String newAttendanceInfo) {
        if (this.attendanceInfo == null) {
            this.attendanceInfo = newAttendanceInfo;
            return;
        }

        // 각 출결 상태의 합계를 누적
        String[] existingParts = this.attendanceInfo.split(" ");
        String[] newParts = newAttendanceInfo.split(" ");

        int existingAtt_o = Integer.parseInt(existingParts[0].substring(2));
        int existingAtt_late = Integer.parseInt(existingParts[1].substring(2));
        int existingAtt_x = Integer.parseInt(existingParts[2].substring(2));
        int existingAtt_etc = Integer.parseInt(existingParts[3].substring(2));

        int newAtt_o = Integer.parseInt(newParts[0].substring(2));
        int newAtt_late = Integer.parseInt(newParts[1].substring(2));
        int newAtt_x = Integer.parseInt(newParts[2].substring(2));
        int newAtt_etc = Integer.parseInt(newParts[3].substring(2));

        int totalAtt_o = existingAtt_o + newAtt_o;
        int totalAtt_late = existingAtt_late + newAtt_late;
        int totalAtt_x = existingAtt_x + newAtt_x;
        int totalAtt_etc = existingAtt_etc + newAtt_etc;

        // 누적된 값을 다시 문자열로 만들어 저장
        this.attendanceInfo = String.format("출석%d 지각%d 결석%d 기타%d", totalAtt_o, totalAtt_late, totalAtt_x, totalAtt_etc);



    }



}