package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.mappers.AppointmentDtoMapper;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.exceptions.DuplicateAppointmentException;
import com.example.spring_patitas.domain.exceptions.PastAppointmentException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CreateAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper mapper;
    
    public AppointmentResponse execute(AppointmentRequest request) {
        // Validar que no sea una cita en el pasado
        validateNotInPast(request.getDate(), request.getTime());
        
        // Validar que no exista una cita en la misma fecha y hora
        appointmentRepository.findByDateAndTime(request.getDate(), request.getTime())
                .ifPresent(existingAppointment -> {
                    throw new DuplicateAppointmentException(request.getDate(), request.getTime());
                });
        
        // La validación de time slot está en el dominio
        Appointment appointment = mapper.toDomain(request);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        return mapper.toResponse(savedAppointment);
    }
    
    private void validateNotInPast(LocalDate date, LocalTime time) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        
        // Si la fecha es anterior a hoy, no permitir
        if (date.isBefore(today)) {
            throw new PastAppointmentException(date);
        }
        
        // Si la fecha es hoy, validar que la hora sea al menos 30 minutos después de la hora actual
        if (date.isEqual(today)) {
            LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
            LocalDateTime minimumDateTime = now.plusMinutes(30);
            
            if (appointmentDateTime.isBefore(minimumDateTime)) {
                // Calcular la siguiente hora válida (en intervalos de 30 minutos)
                LocalTime minimumTime = calculateNextValidTimeSlot(minimumDateTime.toLocalTime());
                throw new PastAppointmentException(date, time, minimumTime);
            }
        }
    }
    
    private LocalTime calculateNextValidTimeSlot(LocalTime currentTime) {
        int minutes = currentTime.getMinute();
        int hour = currentTime.getHour();
        
        // Redondear al siguiente intervalo de 30 minutos
        if (minutes <= 30) {
            return LocalTime.of(hour, 30);
        } else {
            return LocalTime.of(hour + 1, 0);
        }
    }
}
