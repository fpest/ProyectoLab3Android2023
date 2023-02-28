package com.ulp.instituto.modelo;

import java.io.Serializable;

public class MisCarrerasView implements Serializable {

    private int ciclolectivo;
    private String descripcion;
    private String estado;
    private String carreraid;


    public MisCarrerasView() {
    }

    public MisCarrerasView(int ciclolectivo,
                           String descripcion,
                           String estado,
                           String carreraid) {
        this.ciclolectivo = ciclolectivo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.carreraid = carreraid;

    }

    public int getCiclolectivo() {
        return ciclolectivo;
    }

    public void setCiclolectivo(int ciclolectivo) {
        this.ciclolectivo = ciclolectivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCarreraid() {
        return carreraid;
    }

    public void setCarreraid(String carreraid) {
        this.carreraid = carreraid;
    }
}
