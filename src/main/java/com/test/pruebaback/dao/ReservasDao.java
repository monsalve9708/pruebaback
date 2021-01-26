package com.test.pruebaback.dao;

import com.test.pruebaback.model.Reserva;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ReservasDao {
    JdbcTemplate jdbcTemplate;

    public Long reserva(Reserva reserva){

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("reservas")
                .usingGeneratedKeyColumns("nmreserva");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("nmsala",reserva.getNmSala());
        parameters.put("fereserva",reserva.getFeReserva());
        parameters.put("fehacereserva",reserva.getFeHaceReserva());
        parameters.put("horareserva",reserva.getHoraReserva());
        parameters.put("finhorareserva",reserva.getFinHoraReserva());
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
    public List<Reserva> geTDisponibilidad(Integer nmSala, LocalDate feReserva, LocalTime horaInicio, LocalTime horaFin){
        String sql = "SELECT nmsala,fereserva,fehacereserva,nmreserva,horareserva,finhorareserva FROM reservas WHERE nmsala = ? and fereserva = ? and (horareserva = ? or finhorareserva = ?) and febaja is null";
        Object[] args = {nmSala,feReserva,horaInicio,horaFin};
        RowMapper<Reserva> rowMapper = (rs, rowNum) -> Reserva.of(rs.getInt("nmsala"),rs.getDate("fereserva").toLocalDate(),rs.getDate("fehacereserva").toLocalDate(),
                rs.getTime("horareserva").toLocalTime(),rs.getTime("finhorareserva").toLocalTime(), rs.getLong("nmreserva"));
        return Try.of(() -> jdbcTemplate.query(sql,rowMapper,args)).get();
    }
    public List<Reserva> geTDisponibilidad(Integer nmSala, LocalDate feReserva){
        String sql = "SELECT nmsala,fereserva,fehacereserva,nmreserva,horareserva,finhorareserva FROM reservas WHERE nmsala = ? and fereserva = ? and febaja is null";
        Object[] args = {nmSala,feReserva};
        RowMapper<Reserva> rowMapper = (rs, rowNum) -> Reserva.of(rs.getInt("nmsala"),rs.getDate("fereserva").toLocalDate(),rs.getDate("fehacereserva").toLocalDate(),
                rs.getTime("horareserva").toLocalTime(),rs.getTime("finhorareserva").toLocalTime(), rs.getLong("nmreserva"));
        return Try.of(() -> jdbcTemplate.query(sql,rowMapper,args)).get();
    }
    public int eliminarReserva(Long nmReserva){
        String sql = "update reservas set febaja = ? WHERE nmreserva = ?";
        Object[] args = {LocalDate.now(),nmReserva};
        return Try.of(() -> jdbcTemplate.update(sql,args)).get();
    }
    public int updateReserva(Reserva reserva){
        String sql = "update reservas set fereserva = ?, fehacereserva = ?, nmsala = ?,horareserva = ?, finhorareserva = ?  WHERE nmreserva = ? and febaja is null";
        Object[] args = {reserva.getFeReserva(),LocalDateTime.now(), reserva.getNmSala(),reserva.getHoraReserva(),reserva.getFinHoraReserva(),reserva.getNmReserva()};
        return Try.of(() -> jdbcTemplate.update(sql,args)).get();
    }
}
