package com.test.pruebaback.model;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value(staticConstructor = "of")
public class Reserva {
    Integer nmSala;
    LocalDate feReserva;
    LocalDate feHaceReserva;
    LocalTime horaReserva;
    LocalTime finHoraReserva;
    Long nmReserva;
}
