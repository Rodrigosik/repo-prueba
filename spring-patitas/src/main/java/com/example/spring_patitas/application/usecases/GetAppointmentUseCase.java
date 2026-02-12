package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.mappers.AppointmentDtoMapper;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.exceptions.AppointmentNotFoundException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper mapper;
    
    public AppointmentResponse execute(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        
        return mapper.toResponse(appointment);
    }
}
