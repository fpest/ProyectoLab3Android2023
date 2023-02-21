package com.ulp.instituto.modelo;

import java.io.Serializable;

public class MisMateriasView implements Serializable {

    private int ciclolectivo;
    private String descripcion;
    private String estado;


    public MisMateriasView() {
    }

    public MisMateriasView(int ciclolectivo,
                           String descripcion,
                           String estado) {
        this.ciclolectivo = ciclolectivo;
        this.descripcion = descripcion;
        this.estado = estado;

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
}
