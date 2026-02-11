package com.example.spring_patitas.infrastructure.adapters.input.rest;

import com.example.spring_patitas.application.dto.AppointmentRequest;
import com.example.spring_patitas.application.dto.AppointmentResponse;
import com.example.spring_patitas.application.usecases.CreateAppointmentUseCase;
import com.example.spring_patitas.application.usecases.GetAllAppointmentsUseCase;
import com.example.spring_patitas.application.usecases.GetAppointmentUseCase;
import com.example.spring_patitas.application.usecases.UpdateAppointmentStatusUseCase;
import com.example.spring_patitas.domain.enums.AppointmentStatus;
import com.example.spring_patitas.infrastructure.config.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Citas Veterinarias", description = "API para la gestión de citas en la veterinaria")
public class AppointmentController {
    
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;
    private final GetAllAppointmentsUseCase getAllAppointmentsUseCase;
    private final UpdateAppointmentStatusUseCase updateAppointmentStatusUseCase;
    
    @PostMapping
    @Operation(
        summary = "Crear una nueva cita",
        description = "Crea una nueva cita veterinaria con los datos del cliente, mascota y motivo de la consulta. " +
                     "Las citas solo pueden agendarse en intervalos de 30 minutos (09:00, 09:30, 10:00, etc.)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Cita creada exitosamente",
            content = @Content(schema = @Schema(implementation = AppointmentResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos o horario no permitido (debe ser en intervalos de 30 minutos)",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe una cita programada para la fecha y hora especificada",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    public ResponseEntity<AppointmentResponse> createAppointment(
            @Valid @RequestBody @Parameter(description = "Datos de la nueva cita") AppointmentRequest request) {
        AppointmentResponse response = createAppointmentUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Consultar una cita por ID",
        description = "Obtiene los detalles de una cita específica mediante su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cita encontrada",
            content = @Content(schema = @Schema(implementation = AppointmentResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cita no encontrada",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    public ResponseEntity<AppointmentResponse> getAppointment(
            @PathVariable @Parameter(description = "ID de la cita", example = "1") Long id) {
        AppointmentResponse response = getAppointmentUseCase.execute(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(
        summary = "Listar todas las citas",
        description = "Obtiene el listado completo de todas las citas registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de citas obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = AppointmentResponse.class))
        )
    })
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> response = getAllAppointmentsUseCase.execute();
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/status")
    @Operation(
        summary = "Actualizar estado de una cita",
        description = "Cambia el estado de una cita existente (PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = AppointmentResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cita no encontrada",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    public ResponseEntity<AppointmentResponse> updateAppointmentStatus(
            @PathVariable @Parameter(description = "ID de la cita", example = "1") Long id,
            @RequestParam @Parameter(
                description = "Nuevo estado de la cita",
                example = "CONFIRMED",
                schema = @Schema(allowableValues = {"PENDING", "CONFIRMED", "IN_PROGRESS", "COMPLETED", "CANCELLED"})
            ) AppointmentStatus status) {
        AppointmentResponse response = updateAppointmentStatusUseCase.execute(id, status);
        return ResponseEntity.ok(response);
    }
}
