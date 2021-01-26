package com.test.pruebaback.services;

import com.test.pruebaback.dao.CateringDao;
import com.test.pruebaback.model.Catering;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CateringServices {
    CateringDao cateringDao;

    public Catering createCatering(Catering catering){
       return Catering.of(cateringDao.insertCatering(catering),catering.getNmSala(),catering.getDsPedido(),catering.getNmReserva());
    }
}
