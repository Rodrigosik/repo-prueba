package com.example.spring_patitas.application.dto;

import com.example.spring_patitas.domain.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Datos para crear una nueva cita veterinaria")
public class AppointmentRequest {
    
    @NotBlank(message = "Client name is required")
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String clientName;
    
    @NotBlank(message = "Pet name is required")
    @Schema(description = "Nombre de la mascota", example = "Max", requiredMode = Schema.RequiredMode.REQUIRED)
    private String petName;
    
    @NotBlank(message = "Reason is required")
    @Schema(description = "Motivo o razón de la cita", example = "Vacunación anual y chequeo general", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reason;
    
    @NotNull(message = "Date is required")
    @Schema(description = "Fecha de la cita", example = "2026-02-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate date;
    
    @NotNull(message = "Time is required")
    @Schema(description = "Hora de la cita (solo se permiten intervalos de 30 minutos: 09:00, 09:30, 10:00, etc.)", example = "10:30:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalTime time;
    
    @Schema(description = "Estado inicial de la cita (por defecto PENDING)", example = "PENDING", allowableValues = {"PENDING", "COMPLETED"})
    private AppointmentStatus status;
}
