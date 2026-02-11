package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentResponse execute(AppointmentRequest request) {
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
