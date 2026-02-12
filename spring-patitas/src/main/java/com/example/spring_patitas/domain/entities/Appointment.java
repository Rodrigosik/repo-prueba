package com.example.spring_patitas.domain.entities;

import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.domain.exceptions.InvalidTimeSlotException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class Appointment {
    private Long id;
    private String clientName;
    private String petName;
    private String reason;
    private LocalDate date;
    private LocalTime time;
    private AppointmentStatus status;

    private Appointment(Long id, String clientName, String petName, String reason, 
                       LocalDate date, LocalTime time, AppointmentStatus status) {
        this.id = id;
        this.clientName = clientName;
        this.petName = petName;
        this.reason = reason;
        this.date = date;
        this.time = time;
        this.status = status != null ? status : AppointmentStatus.PENDING;
        
        validateTimeSlot(time);
    }

    public static Appointment create(String clientName, String petName, String reason, 
                                    LocalDate date, LocalTime time) {
        return new Appointment(null, clientName, petName, reason, date, time, AppointmentStatus.PENDING);
    }

    public static Appointment createWithStatus(String clientName, String petName, String reason, 
                                              LocalDate date, LocalTime time, AppointmentStatus status) {
        return new Appointment(null, clientName, petName, reason, date, time, status);
    }

    public static Appointment reconstitute(Long id, String clientName, String petName, String reason, 
                                          LocalDate date, LocalTime time, AppointmentStatus status) {
        return new Appointment(id, clientName, petName, reason, date, time, status);
    }

    private void validateTimeSlot(LocalTime time) {
        int minutes = time.getMinute();
        if (minutes != 0 && minutes != 30) {
            throw new InvalidTimeSlotException(time);
        }
    }

    public void complete() {
        this.status = AppointmentStatus.COMPLETED;
    }

   
    public void updateStatus(AppointmentStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = newStatus;
    }

    public boolean isPending() {
        return this.status == AppointmentStatus.PENDING;
    }

    public boolean isCompleted() {
        return this.status == AppointmentStatus.COMPLETED;
    }  
}
