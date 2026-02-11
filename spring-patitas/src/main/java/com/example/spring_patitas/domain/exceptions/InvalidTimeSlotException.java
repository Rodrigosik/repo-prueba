package com.example.spring_patitas.domain.exceptions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InvalidTimeSlotException extends RuntimeException {
    
    public InvalidTimeSlotException(LocalTime time) {
        super(String.format("La hora %s no es válida. Las citas solo pueden agendarse en intervalos de 30 minutos (ejemplo: 09:00, 09:30, 10:00, 10:30, etc.)",
                time.format(DateTimeFormatter.ofPattern("HH:mm"))));
    }
}
