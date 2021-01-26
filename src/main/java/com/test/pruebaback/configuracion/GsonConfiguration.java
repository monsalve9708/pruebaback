package com.test.pruebaback.configuracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.pruebaback.model.Catering;
import com.test.pruebaback.model.Reserva;
import com.test.pruebaback.serialization.CateringSerialization;
import com.test.pruebaback.serialization.ReservaSerialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {
    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .registerTypeAdapter(Reserva.class,new ReservaSerialization())
                .registerTypeAdapter(Catering.class,new CateringSerialization())
                .create();
    }
}
