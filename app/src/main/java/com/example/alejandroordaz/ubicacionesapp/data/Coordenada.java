package com.example.alejandroordaz.ubicacionesapp.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Alejandro Ordaz on 08/07/2017.
 */


//necesito tener cargadas las dependencias de ormlite
@DatabaseTable (tableName = "coordenada")

public class Coordenada implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false, index = true)
    private String nombre;
    @DatabaseField
    private double lat;



    @DatabaseField

    private double longi;


    @Override
    public String toString() {

        return ""+this.nombre+" Lat"+this.lat+" Long"+this.longi;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public Coordenada(){

    }

    public Coordenada(String nombre, double lat, double longi) {
        this.nombre = nombre;
        this.lat = lat;
        this.longi = longi;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public int getId() {
        return id;
    }



}
