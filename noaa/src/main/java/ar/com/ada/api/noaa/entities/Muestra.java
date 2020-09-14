package ar.com.ada.api.noaa.entities;

import java.sql.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "muestras")
public class Muestra {
    private ObjectId _id;
    private Date horaMuestra;
    private String matriculaEmbarcacion;
    private Double latitud;
    private Double longitud;
    private Double alturaNivelMar;

    /**
     * @return the _id
     */
    public ObjectId get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    /**
     * @return the horaMuestra
     */
    public Date getHoraMuestra() {
        return horaMuestra;
    }

    /**
     * @param horaMuestra the horaMuestra to set
     */
    public void setHoraMuestra(Date horaMuestra) {
        this.horaMuestra = horaMuestra;
    }

    /**
     * @return the matriculaEmbarcacion
     */
    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }

    /**
     * @param matriculaEmbarcacion the matriculaEmbarcacion to set
     */
    public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the alturaNivelMar
     */
    public Double getAlturaNivelMar() {
        return alturaNivelMar;
    }

    /**
     * @param alturaNivelMar the alturaNivelMar to set
     */
    public void setAlturaNivelMar(Double alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

}