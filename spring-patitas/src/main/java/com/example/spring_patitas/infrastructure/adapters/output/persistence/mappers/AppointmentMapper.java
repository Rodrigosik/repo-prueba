package com.example.spring_patitas.infrastructure.adapters.output.persistence.mappers;

import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.infrastructure.adapters.output.persistence.entities.AppointmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    
    public AppointmentEntity toEntity(Appointment appointment) {
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .clientName(appointment.getClientName())
                .petName(appointment.getPetName())
                .reason(appointment.getReason())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status(appointment.getStatus())
                .build();
    }
    
    public Appointment toDomain(AppointmentEntity entity) {
        return Appointment.reconstitute(
                entity.getId(),
                entity.getClientName(),
                entity.getPetName(),
                entity.getReason(),
                entity.getDate(),
                entity.getTime(),
                entity.getStatus()
        );
    }
}
