package ar.com.ada.api.noaa.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.model.request.BoyaColorRequest;
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
        gR.id = boya.get_id().toHexString();
        gR.message = "Boya creada con exito";
        return ResponseEntity.ok(gR);
    }

    @GetMapping("/boyas")
    public ResponseEntity<?> listarBoyas() {
        return ResponseEntity.ok(boyaService.listarBoyas());
    }

    @GetMapping("/boyas/{id}")
    public ResponseEntity<?> obtenerBoyaSegunId(@PathVariable ObjectId id) {
        return ResponseEntity.ok(boyaService.buscarBoyaPorId(id));
    }

    @PutMapping("/boyas/{id}")
    public ResponseEntity<?> modificarBoya(@PathVariable ObjectId id, @RequestBody BoyaColorRequest boyaColor) {
        GenericResponse r = new GenericResponse();
        Boya boya = boyaService.buscarBoyaPorId(id);
        if (boya == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boya.setColorLuz(boyaColor.color);
        boyaService.grabar(boya);

        /*
         * boolean resultado = boyaService.actualizarBoya(boya, newBoya); if (resultado)
         * {
         */
        r.isOk = true;
        r.id = id.toHexString();
        r.message = "Color de boya actualizado con exito";
        return ResponseEntity.ok(r);

    }
}