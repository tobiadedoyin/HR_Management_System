package com.progrd.HR_MANAGEMENT_SYSTEM.repository;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AttendanceDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Attendance;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT new com.progrd.HR_MANAGEMENT_SYSTEM.dto" +
            ".AttendanceDto(a.date, a.employees.firstName, a.employees.lastName, a.signIn, a.signOut, " +
            "a.hourWorked, a.attendanceStatus) " +
            "FROM Attendance a WHERE a.employees.id=:id")
    List<AttendanceDto> findByEmployeesId(@Param("id") long id);

    @Query("SELECT new com.progrd.HR_MANAGEMENT_SYSTEM.dto" +
            ".AttendanceDto(a.date, a.employees.firstName, a.employees.lastName, a.signIn, a.signOut, " +
            "a.hourWorked, a.attendanceStatus) " +
            "FROM Attendance a WHERE a.date=:date")
    List<AttendanceDto> findAttendanceDtoByDate(@Param("date") LocalDate date);

    List<Attendance> findAttendanceByDate(LocalDate date);

    List<Attendance> findAttendanceByEmployeesAndDateBetween(Employee employees,
                                                             LocalDate date, LocalDate date2);
    List<Attendance> findAttendanceByDateBetween(LocalDate date, LocalDate date2);

}
