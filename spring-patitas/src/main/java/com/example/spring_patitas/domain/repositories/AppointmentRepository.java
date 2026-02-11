package com.example.spring_patitas.domain.repositories;

import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.enums.AppointmentStatus;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(Long id);
    List<Appointment> findAll();
    Appointment updateStatus(Long id, AppointmentStatus status);
}
