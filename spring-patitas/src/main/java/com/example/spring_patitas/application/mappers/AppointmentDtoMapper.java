package com.example.spring_patitas.application.mappers;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.domain.entities.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDtoMapper {
    
    public Appointment toDomain(AppointmentRequest request) {
        if (request.getStatus() != null) {
            return Appointment.createWithStatus(
                    request.getClientName(),
                    request.getPetName(),
                    request.getReason(),
                    request.getDate(),
                    request.getTime(),
                    request.getStatus()
            );
        } else {
            return Appointment.create(
                    request.getClientName(),
                    request.getPetName(),
                    request.getReason(),
                    request.getDate(),
                    request.getTime()
            );
        }
    }
    
    public AppointmentResponse toResponse(Appointment appointment) {
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
