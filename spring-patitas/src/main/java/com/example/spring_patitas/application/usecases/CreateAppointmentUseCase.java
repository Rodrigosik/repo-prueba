package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.mappers.AppointmentDtoMapper;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.exceptions.DuplicateAppointmentException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper mapper;
    
    public AppointmentResponse execute(AppointmentRequest request) {
        // Validar que no exista una cita en la misma fecha y hora
        appointmentRepository.findByDateAndTime(request.getDate(), request.getTime())
                .ifPresent(existingAppointment -> {
                    throw new DuplicateAppointmentException(request.getDate(), request.getTime());
                });
        
        // La validación de time slot ahora está en el dominio
        Appointment appointment = mapper.toDomain(request);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        return mapper.toResponse(savedAppointment);
    }
}
