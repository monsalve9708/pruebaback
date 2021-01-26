package com.test.pruebaback.serialization;

import com.google.gson.*;
import com.test.pruebaback.model.Catering;
import com.test.pruebaback.model.Reserva;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CateringSerialization implements JsonSerializer<Catering>, JsonDeserializer<Catering> {

    @Override
    public Catering deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Long nmCatering = Optional.of(jsonObject.get("nmCatering").getAsLong()).orElse(0L);
        return Catering.of(nmCatering,jsonObject.get("nmSala").getAsInt(),jsonObject.
                get("dsPedido").getAsString(),jsonObject.get("nmReserva").getAsInt());
    }

    @Override
    public JsonElement serialize(Catering src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("nmCatering",src.getNmCatering());
        object.addProperty("nmSala",src.getNmSala());
        object.addProperty("dsPedido",src.getDsPedido());
        object.addProperty("nmReserva",src.getNmReserva());
        return object;
    }
}
