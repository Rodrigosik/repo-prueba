package com.example.spring_patitas.infrastructure.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Respuesta de error estándar de la API")
public class ErrorResponse {
    
    @Schema(description = "Fecha y hora del error", example = "2026-02-11T10:30:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "Código de estado HTTP", example = "400")
    private int status;
    
    @Schema(description = "Tipo de error", example = "Bad Request")
    private String error;
    
    @Schema(description = "Mensaje descriptivo del error", example = "Ya existe una cita en esa fecha y hora")
    private String message;
    
    @Schema(description = "Ruta del endpoint donde ocurrió el error", example = "/api/appointments")
    private String path;
    
    @Schema(description = "Detalles adicionales del error (usado para validaciones)")
    private Map<String, String> errors;
}
