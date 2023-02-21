package com.ulp.instituto.modelo;

import java.io.Serializable;

public class Carrera implements Serializable {

    private int id;
    private String descripcion;
    private int ciclolectivo;


    public Carrera() {
    }

    public Carrera(int id,
                   String descripcion,
                   int ciclolectivo) {
        this.id = id;
        this.descripcion = descripcion;
        this.ciclolectivo = ciclolectivo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCiclolectivo() {
        return ciclolectivo;
    }

    public void setCiclolectivo(int ciclolectivo) {
        this.ciclolectivo = ciclolectivo;
    }
}
