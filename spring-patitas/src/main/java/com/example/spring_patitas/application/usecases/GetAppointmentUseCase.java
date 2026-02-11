package com.example.spring_patitas.application.usecases;

import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.domain.entities.Appointment;
import com.example.spring_patitas.domain.exceptions.AppointmentNotFoundException;
import com.example.spring_patitas.domain.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAppointmentUseCase {
    
    private final AppointmentRepository appointmentRepository;
    
    public AppointmentResponse execute(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        
        return mapToResponse(appointment);
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
