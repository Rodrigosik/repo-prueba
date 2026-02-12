package com.example.spring_patitas.infrastructure.adapters.output.persistence;

import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import com.example.spring_patitas.infrastructure.adapters.output.persistence.entities.AppointmentEntity;
import com.example.spring_patitas.infrastructure.adapters.output.persistence.mappers.AppointmentMapper;
import com.example.spring_patitas.infrastructure.adapters.output.persistence.repositories.AppointmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {
    
    private final AppointmentJpaRepository jpaRepository;
    private final AppointmentMapper mapper;
    
    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = mapper.toEntity(appointment);
        AppointmentEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Appointment> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Appointment> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Appointment> findByDateAndTime(java.time.LocalDate date, java.time.LocalTime time) {
        return jpaRepository.findByDateAndTime(date, time)
                .map(mapper::toDomain);
    }
}
