package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAppointmentStatusUseCase {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentResponse execute(Long id, AppointmentStatus status) {
        Appointment updatedAppointment = appointmentRepository.updateStatus(id, status);
        
        return mapToResponse(updatedAppointment);
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
