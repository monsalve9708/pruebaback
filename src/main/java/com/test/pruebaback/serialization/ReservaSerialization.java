package com.test.pruebaback.serialization;

import com.google.gson.*;
import com.test.pruebaback.model.Reserva;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ReservaSerialization implements JsonSerializer<Reserva>, JsonDeserializer<Reserva> {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public Reserva deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        Long nmReserva = Optional.of(jsonObject.get("nmReserva").getAsLong()).orElse(0L);
        return Reserva.of(jsonObject.get("nmSala").getAsInt(), LocalDate.parse(jsonObject.get("feReserva").getAsString(),format),
                LocalDate.parse(jsonObject.get("feHaceReserva").getAsString(),format),
                LocalTime.parse(jsonObject.get("horaReserva").getAsString(),formatTime),
                LocalTime.parse(jsonObject.get("finHoraReserva").getAsString(),formatTime),nmReserva);
    }

    @Override
    public JsonElement serialize(Reserva src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("nmSala",src.getNmSala());
        object.addProperty("nmReserva",src.getNmReserva());
        object.addProperty("feReserva",src.getFeReserva().toString());
        object.addProperty("feHaceReserva",src.getFeHaceReserva().toString());
        object.addProperty("horaReserva",src.getHoraReserva().toString());
        object.addProperty("finHoraReserva",src.getFinHoraReserva().toString());
        return object;
    }
}
