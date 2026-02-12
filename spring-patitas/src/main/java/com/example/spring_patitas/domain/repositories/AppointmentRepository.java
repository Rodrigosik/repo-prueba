package com.example.spring_patitas.domain.repositories;

import com.example.spring_patitas.domain.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(Long id);
    List<Appointment> findAll();
    Optional<Appointment> findByDateAndTime(LocalDate date, LocalTime time);
}
