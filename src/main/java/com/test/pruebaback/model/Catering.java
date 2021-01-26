package com.test.pruebaback.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Catering {
    Long nmCatering;
    Integer nmSala;
    String dsPedido;
    Integer nmReserva;

    public Catering(Long nmCatering, Integer nmSala, String dsPedido, Integer nmReserva) {
        this.nmCatering = nmCatering;
        this.nmSala = nmSala;
        this.dsPedido = dsPedido;
        this.nmReserva = nmReserva;
    }
}
