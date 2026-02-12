package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.mappers.AppointmentDtoMapper;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllAppointmentsUseCase {
    
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper mapper;
    
    public List<AppointmentResponse> execute() {
        List<Appointment> appointments = appointmentRepository.findAll();
        
        return appointments.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
