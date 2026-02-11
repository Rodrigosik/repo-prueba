package com.example.spring_patitas.domain.entities;

import com.example.spring_patitas.domain.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Long id;
    private String clientName;
    private String petName;
    private String reason;
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;

    public void updateStatus(AppointmentStatus newStatus) {
        this.status = newStatus;
    }
}
