package ar.com.ada.api.noaa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.model.request.MuestraRequest;
import ar.com.ada.api.noaa.model.response.GenericResponse;
import ar.com.ada.api.noaa.model.response.ListarMuestraResponse;
import ar.com.ada.api.noaa.model.response.MuestraResponse;
import ar.com.ada.api.noaa.services.BoyaService;
import ar.com.ada.api.noaa.services.MuestraService;

@RestController
public class MuestraController {
    @Autowired
    MuestraService muestraService;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<?> crearMuestra(@RequestBody MuestraRequest muestraRequest) {
        GenericResponse r = new GenericResponse();
        MuestraResponse muestraCreada = new MuestraResponse();
        Muestra muestra = muestraService.crearMuestra(muestraRequest.id, muestraRequest.horario,
                muestraRequest.matricula, muestraRequest.longitud, muestraRequest.latitud,
                muestraRequest.alturaNivelDelMar);
        if (muestra == null) {
            r.isOk = false;
            r.message = "No pudiste crear la muestra";
            return ResponseEntity.badRequest().body(r);
        }

        muestraCreada.id = muestra.get_id().toHexString();
        muestraCreada.color = boyaService.buscarBoyaPorId(muestraRequest.id).getColorLuz();
        return ResponseEntity.ok(muestraCreada);
    }

    @GetMapping("/muestras/boyas/{id}")
    public ResponseEntity<?> listarMuestrasPorBoya(@PathVariable ObjectId id) {

        return ResponseEntity.ok(this.boyaService.boyaPorId(id).getMuestras().size());

    }

    @GetMapping("/muestras")
    public ResponseEntity<?> listarMuestras() {
        List<ListarMuestraResponse> muestrasTodas = new ArrayList<>();
        this.muestraService.listarMuestras().stream().forEach(muestra -> {
            ListarMuestraResponse mu = new ListarMuestraResponse();
            mu.id = muestra.get_id().toHexString();
            mu.horaMuestra = muestra.getHoraMuestra();
            mu.alturaNivelMar = muestra.getAlturaNivelMar();
            mu.longitud = muestra.getLongitud();
            mu.latitud = muestra.getLatitud();
            mu.matriculaEmbarcacion = muestra.getMatriculaEmbarcacion();
            muestrasTodas.add(mu);
        });

        return ResponseEntity.ok(muestrasTodas);
    }

}
