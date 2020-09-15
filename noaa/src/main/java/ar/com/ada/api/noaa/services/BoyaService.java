package ar.com.ada.api.noaa.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
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

    public Boya buscarBoyaPorId(ObjectId id) {
        Optional<Boya> boya = this.listarBoyas().stream().filter(b -> b.get_id().equals(id)).findFirst();
        return (boya.isPresent() ? boya.get() : null);

    }

    public Boya boyaPorId(ObjectId id) {
        return boyaRepository.findBy_id(id);

    }

}
