package ar.com.ada.api.noaa.model.request;

import java.util.Date;

import org.bson.types.ObjectId;

public class MuestraRequest {
    public ObjectId id;
    public Date horario;
    public String matricula;
    public Double longitud;
    public Double latitud;
    public Double alturaNivelDelMar;

}
