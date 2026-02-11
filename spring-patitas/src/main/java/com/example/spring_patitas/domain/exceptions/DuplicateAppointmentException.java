package com.example.spring_patitas.domain.exceptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DuplicateAppointmentException extends RuntimeException {
    
    public DuplicateAppointmentException(LocalDate date, LocalTime time) {
        super(String.format("Ya existe una cita programada para la fecha %s a las %s. Por favor, seleccione otro horario.",
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                time.format(DateTimeFormatter.ofPattern("HH:mm"))));
    }
}
