package com.example.spring_patitas.domain.exceptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PastAppointmentException extends RuntimeException {
    
    public PastAppointmentException(LocalDate date, LocalTime time, LocalTime minimumTime) {
        super(String.format("No se puede agendar una cita en el pasado. La fecha y hora seleccionadas (%s %s) ya han transcurrido. La hora mínima disponible para hoy es %s.",
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                time.format(DateTimeFormatter.ofPattern("hh:mm a")),
                minimumTime.format(DateTimeFormatter.ofPattern("hh:mm a"))));
    }
    
    public PastAppointmentException(LocalDate date) {
        super(String.format("No se puede agendar una cita en el pasado. La fecha seleccionada (%s) ya ha transcurrido.",
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }
}
