package com.example.spring_patitas.infrastructure.adapters.output.persistence.repositories;

import com.example.spring_patitas.infrastructure.adapters.output.persistence.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {
    Optional<AppointmentEntity> findByDateAndTime(LocalDate date, LocalTime time);
}
