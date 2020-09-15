package ar.com.ada.api.noaa.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.noaa.entities.Muestra;

@Repository
public interface MuestraRepository extends MongoRepository<Muestra, ObjectId> {

    Muestra findBy_id(ObjectId id);
}
