package com.test.pruebaback.dao;

import com.test.pruebaback.model.Catering;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CateringDao {
    JdbcTemplate jdbcTemplate;

    public Long insertCatering(Catering catering){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("catering")
                .usingGeneratedKeyColumns("nmcatering");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("dspedido",catering.getDsPedido());
        parameters.put("nmsala",catering.getNmSala());
        parameters.put("nmreserva",catering.getNmReserva());
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
