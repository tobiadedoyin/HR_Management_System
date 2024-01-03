package com.progrd.HR_MANAGEMENT_SYSTEM.schedule;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.PresentEmployeeId;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import com.progrd.HR_MANAGEMENT_SYSTEM.enums.AttendanceStatus;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.AttendanceRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttendanceSchedule {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "0 30 23 * * MON-FRI", zone = "Africa/Lagos")
    @Transactional
    public void markAbsentee() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Africa/Lagos"));
        long numberOfEmployee = employeeRepository.count();
        List<Attendance> attendanceListFotTheDay = attendanceRepository.findAttendanceByDate(currentDate);
        long attendanceCountForTheDay = attendanceListFotTheDay.size();

        List<PresentEmployeeId> employeeId = attendanceListFotTheDay.stream()
                .map(a -> new PresentEmployeeId(a.getEmployees().getId())).toList();


        if (attendanceCountForTheDay != numberOfEmployee) {

            for (long i = 1; i <= numberOfEmployee; i++) {
                if (employeeRepository.existsById(i)) {
                    if (!employeeId.contains(new PresentEmployeeId(i))) {
                        Employee employee = employeeRepository.findById(i).orElseThrow();

                        attendanceRepository.save(new Attendance(employee, currentDate,
                                LocalTime.of(0, 0), LocalTime.now(ZoneId.of("Africa/Lagos")), 0,
                                 AttendanceStatus.ABSENT));
                    }
                }
            }
        }
    }
}
