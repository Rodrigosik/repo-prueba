package com.example.spring_patitas.application.dto;

import com.example.spring_patitas.domain.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(max = 50, message = "Client name must not exceed 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚa-zñáéíóúÑüÜ ]+$", message = "Client name contains invalid characters")
    @Schema(description = "Nombre completo del cliente (máximo 50 caracteres, solo letras, espacios, guiones y apóstrofes)", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String clientName;
    
    @NotBlank(message = "Pet name is required")
    @Size(max = 50, message = "Pet name must not exceed 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚa-zñáéíóúÑüÜ ]+$", message = "Pet name contains invalid characters")
    @Schema(description = "Nombre de la mascota (máximo 50 caracteres, solo letras, espacios, guiones y apóstrofes)", example = "Max", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String petName;
    
    @NotBlank(message = "Reason is required")
    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9., ]+$", message = "Reason contains invalid characters")
    @Schema(description = "Motivo o razón de la cita (máximo 500 caracteres, solo letras, números, espacios, puntos y comas)", example = "Vacunación anual y chequeo general", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 500)
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
