package com.test.pruebaback.controller;

import com.test.pruebaback.model.Reserva;
import com.test.pruebaback.services.ReservasServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("api/v1/reservas")
@RestController
@AllArgsConstructor
public class ReservasController {
    ReservasServices reservasServices;

    @PostMapping("/create")
    public ResponseEntity<Object> createReserva(@RequestBody Reserva reserva){
       Object reservaResponse = reservasServices.createReserva(reserva);
       if (reservaResponse == null){
           return ResponseEntity.badRequest().body(null);
       }
       return ResponseEntity.ok(reservaResponse);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<LocalTime>> getDisponibilidad(@RequestParam("nmsala") Integer nmSala,@RequestParam("feReserva") String feReserva){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaReserva = LocalDate.parse(feReserva,format);
        List<LocalTime> disponibilidad = reservasServices.getDisponibilidad(nmSala,fechaReserva);
        if (disponibilidad.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(disponibilidad);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deteleReserva(@RequestParam("nmReserva") Long nmReserva){
        int response = reservasServices.delete(nmReserva);
        if (response == 1){
            return ResponseEntity.ok("La reserva "+nmReserva+" se ha eliminado correctamente");
        }
        return ResponseEntity.badRequest().body("Ha ocurrido un error eliminando la reserva, vuelva a intentar por favor");
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateReserva(@RequestBody Reserva reserva){
        int reservaResponse = reservasServices.updateReser(reserva);
        if (reservaResponse != 1){
            return ResponseEntity.badRequest().body("Ha ocurrido un error actualizando la reserva, vuelva a intentar por favor");
        }
        return ResponseEntity.ok("La reserva "+reserva.getNmReserva()+" se ha actualizado correctamente");
    }

}
