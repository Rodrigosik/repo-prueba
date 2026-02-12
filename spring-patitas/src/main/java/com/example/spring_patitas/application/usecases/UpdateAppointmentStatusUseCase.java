package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.mappers.AppointmentDtoMapper;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.domain.exceptions.AppointmentNotFoundException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAppointmentStatusUseCase {
    
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper mapper;
    
    public AppointmentResponse execute(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        
        // La lógica de negocio está en el dominio
        appointment.updateStatus(status);
        
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        
        return mapper.toResponse(updatedAppointment);
    }
}
