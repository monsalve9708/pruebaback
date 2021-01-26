package com.test.pruebaback.controller;

import com.test.pruebaback.model.Catering;
import com.test.pruebaback.services.CateringServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/catering")
@AllArgsConstructor
public class CateringController {
    CateringServices cateringServices;

    @PostMapping("/create")
    public ResponseEntity<Catering> createCatering(@RequestBody Catering catering){
        Catering cateringResponse = cateringServices.createCatering(catering);
        if (cateringResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(cateringResponse);
    }

}
