package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
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
    
    public List<AppointmentResponse> execute() {
        List<Appointment> appointments = appointmentRepository.findAll();
        
        return appointments.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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
