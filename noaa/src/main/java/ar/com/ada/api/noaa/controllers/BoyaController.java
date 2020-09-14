package ar.com.ada.api.noaa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.model.request.BoyaRequest;
import ar.com.ada.api.noaa.model.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController
public class BoyaController {
    @Autowired
    BoyaService boyaService;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody BoyaRequest boyaNueva) {
        Boya boya = new Boya();
        boya.setLongitudInstalacion(boyaNueva.longitudInstalacion);
        boya.setLatitudInstalacion(boyaNueva.latitudInstalacion);
        boyaService.crearBoya(boya);
        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.id = boya.get_id();
        gR.message = "Boya creada con exito";
        return ResponseEntity.ok(gR);
    }

    @GetMapping("/boyas")
    public ResponseEntity<?> listarBoyas() {
        return ResponseEntity.ok(boyaService.listarBoyas());
    }
}