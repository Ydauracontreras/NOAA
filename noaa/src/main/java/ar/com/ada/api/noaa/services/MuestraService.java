package ar.com.ada.api.noaa.services;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository muestraRepository;
    @Autowired
    BoyaService boyaService;

    public boolean grabar(Muestra muestra) {
        muestraRepository.save(muestra);
        return true;
    }

    public Muestra crearMuestra(ObjectId id, Date horario, String matricula, Double longitud, Double latitud,
            Double alturaNivelMar) {
        Muestra muestra = new Muestra();
        Boya boya = boyaService.buscarBoyaPorId(id);
        muestra.setMatriculaEmbarcacion(matricula);
        muestra.setLongitud(longitud);
        muestra.setLatitud(latitud);
        muestra.setHoraMuestra(horario);
        muestra.setAlturaNivelMar(alturaNivelMar);
        boya.getMuestras().add(muestra);
        boya.setColorLuz(definirColor(alturaNivelMar));
        boya.getMuestras().add(muestra);
        boyaService.grabar(boya);
        grabar(muestra);
        return muestra;
    }

    public String definirColor(Double alturaNivelMar) {
        if (alturaNivelMar < -100 || alturaNivelMar > 100) {
            return "ROJO";
        } else

        if (alturaNivelMar < -50 || alturaNivelMar > 50) {
            return "AMARILLO";
        } else {
            return "VERDE";

        }
    }

    public Muestra buscarMuestra(ObjectId id) {
        Optional<Muestra> muestra = this.listarMuestras().stream().filter(m -> m.get_id().equals(id)).findFirst();
        return (muestra.isPresent() ? muestra.get() : null);
    }

    public List<Muestra> listarMuestras() {
        return muestraRepository.findAll();
    }

}