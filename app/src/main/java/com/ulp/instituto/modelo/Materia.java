package com.ulp.instituto.modelo;

import java.io.Serializable;

public class Materia implements Serializable {

    private int id;
    private String descripcion;
    private int carreraid;


    public Materia() {
    }

    public Materia(int id,
                   String descripcion,
                   int carreraid) {
        this.id = id;
        this.descripcion = descripcion;
        this.carreraid = carreraid;

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

    public int getCarreraId() {
        return carreraid;
    }

    public void setCarreraId(int carreraid) {
        this.carreraid = carreraid;
    }
}
