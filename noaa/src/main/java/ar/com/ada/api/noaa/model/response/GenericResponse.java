package ar.com.ada.api.noaa.model.response;

import org.bson.types.ObjectId;

public class GenericResponse {
    public boolean isOk;
    public String message = " ";
    public ObjectId id;
}