package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.domain.exceptions.DuplicateAppointmentException;
import com.example.spring_patitas.domain.exceptions.InvalidTimeSlotException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentResponse execute(AppointmentRequest request) {
        // Validar que la hora sea en intervalos de 30 minutos (00 o 30)
        int minutes = request.getTime().getMinute();
        if (minutes != 0 && minutes != 30) {
            throw new InvalidTimeSlotException(request.getTime());
        }
        
        // Validar que no exista una cita en la misma fecha y hora
        appointmentRepository.findByDateAndTime(request.getDate(), request.getTime())
                .ifPresent(existingAppointment -> {
                    throw new DuplicateAppointmentException(request.getDate(), request.getTime());
                });
        
        Appointment appointment = Appointment.builder()
                .clientName(request.getClientName())
                .petName(request.getPetName())
                .reason(request.getReason())
                .date(request.getDate())
                .time(request.getTime())
                .status(request.getStatus() != null ? request.getStatus() : AppointmentStatus.PENDING)
                .build();
        
        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        return mapToResponse(savedAppointment);
    }
    
    private AppointmentResponse mapToResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .clientName(appointment.getClientName())
                .petName(appointment.getPetName())
                .reason(appointment.getReason())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .build();
    }
}
