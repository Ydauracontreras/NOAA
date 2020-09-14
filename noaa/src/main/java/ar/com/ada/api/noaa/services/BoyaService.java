package ar.com.ada.api.noaa.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository boyaRepository;

    public boolean grabar(Boya boya) {
        boyaRepository.save(boya);
        return true;
    }

    public void crearBoya(Boya boya) {
        grabar(boya);
    }

    public Boya crearBoya(Double longitud, Double latitud) {
        Boya boya = new Boya();
        boya.setLatitudInstalacion(longitud);
        boya.setLongitudInstalacion(latitud);
        grabar(boya);
        return boya;

    }

    public List<Boya> listarBoyas() {
        return boyaRepository.findAll();
    }

    public Boya BuscarBoyaPorId(ObjectId id) {
        Optional<Boya> boya = boyaRepository.findById(id);
        if (boya.isPresent())
            return boya.get();
        return null;

    }

    public Muestra crearMuestra(ObjectId id, Date horario, String matricula, Double longitud, Double latitud,
            Double alturaNivelMar) {
        Muestra muestra = new Muestra();
        Boya boya = this.BuscarBoyaPorId(id);
        muestra.setHoraMuestra(horario);
        muestra.setMatriculaEmbarcacion(matricula);
        muestra.setLongitud(longitud);
        muestra.setLatitud(latitud);
        muestra.setAlturaNivelMar(alturaNivelMar);
        boya.getMuestras().add(muestra);
        grabar(boya);
        return muestra;

    }

}
