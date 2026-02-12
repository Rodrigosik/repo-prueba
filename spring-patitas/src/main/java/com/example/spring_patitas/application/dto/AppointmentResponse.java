package com.example.spring_patitas.application.dto;

import com.example.spring_patitas.domain.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con los datos completos de una cita veterinaria")
public class AppointmentResponse {
    
    @Schema(description = "ID único de la cita", example = "1")
    private Long id;
    
    @Schema(description = "Nombre completo del cliente (máximo 50 caracteres)", example = "Juan Pérez", maxLength = 50)
    private String clientName;
    
    @Schema(description = "Nombre de la mascota (máximo 50 caracteres)", example = "Max", maxLength = 50)
    private String petName;
    
    @Schema(description = "Motivo o razón de la cita (máximo 500 caracteres)", example = "Vacunación anual y chequeo general", maxLength = 500)
    private String reason;
    
    @Schema(description = "Fecha de la cita", example = "2026-02-15")
    private LocalDate date;
    
    @Schema(description = "Hora de la cita", example = "10:30:00")
    private LocalTime time;
    
    @Schema(description = "Estado actual de la cita", example = "PENDING", allowableValues = {"PENDING", "COMPLETED"})
    private AppointmentStatus status;
}
