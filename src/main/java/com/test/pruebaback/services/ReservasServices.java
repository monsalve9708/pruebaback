package com.test.pruebaback.services;

import com.test.pruebaback.dao.ReservasDao;
import com.test.pruebaback.model.Reserva;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservasServices {
    ReservasDao reservasDao;

    public Object createReserva(Reserva reserva){
        List<Reserva> reservas = reservasDao.geTDisponibilidad(reserva.getNmSala(),reserva.getFeReserva(),reserva.getHoraReserva(),reserva.getFinHoraReserva());
        if (reservas.isEmpty()){
        return Reserva.of(reserva.getNmSala(),reserva.getFeReserva(),reserva.getFeHaceReserva(),reserva.getHoraReserva(),reserva.getFinHoraReserva(),reservasDao.reserva(reserva));
        }
        return "La hora para la reserva ya contiene reserva";
    }
    public List<LocalTime> getDisponibilidad(Integer nmSala, LocalDate feReserva){
        List<LocalTime> disponibilidad =listhorarios();
        List<Reserva> reservas = reservasDao.geTDisponibilidad(nmSala,feReserva);
        System.out.println(reservas);
        reservas.forEach(data -> {
            for (int i = data.getHoraReserva().getHour(); i<data.getFinHoraReserva().getHour();i++){
                LocalTime valor = LocalTime.of(i,0);
                disponibilidad.remove(valor);
            }
        });
        return disponibilidad;
    }
    public List<LocalTime> listhorarios(){
        List<LocalTime> disponibilidad = new ArrayList<>();
        int value = 8;
        for (int i = 0; i <= 10; i++){
            disponibilidad.add(LocalTime.of(value,0));
            value++;
        }
        return disponibilidad;
    }
    public int delete(Long nmReserva){
        return reservasDao.eliminarReserva(nmReserva);
    }
    public int updateReser(Reserva reserva){
        List<Reserva> reservas = reservasDao.geTDisponibilidad(reserva.getNmSala(),reserva.getFeReserva(),reserva.getHoraReserva(),reserva.getFinHoraReserva());
        if (reservas.isEmpty()) {
            return reservasDao.updateReserva(reserva);
        }
        return 0;
    }
}
