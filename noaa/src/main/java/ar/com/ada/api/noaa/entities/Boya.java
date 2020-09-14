package ar.com.ada.api.noaa.entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boyas")
public class Boya {
    private ObjectId _id;
    private String colorLuz;
    private Double longitudInstalacion;
    private Double latitudInstalacion;
    private List<Muestra> muestras = new ArrayList<>();

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
     * @return the longitudInstalacion
     */
    public Double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    /**
     * @param longitudInstalacion the longitudInstalacion to set
     */
    public void setLongitudInstalacion(Double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    /**
     * @return the latitudInstalacion
     */
    public Double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    /**
     * @param latitudInstalacion the latitudInstalacion to set
     */
    public void setLatitudInstalacion(Double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    /**
     * @return the muestras
     */
    public List<Muestra> getMuestras() {
        return muestras;
    }

    /**
     * @param muestras the muestras to set
     */
    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public Object getColor() {
        return null;
    }

    /**
     * @return the colorLuz
     */
    public String getColorLuz() {
        return colorLuz;
    }

    /**
     * @param colorLuz the colorLuz to set
     */
    public void setColorLuz(String colorLuz) {
        this.colorLuz = colorLuz;
    }

}
