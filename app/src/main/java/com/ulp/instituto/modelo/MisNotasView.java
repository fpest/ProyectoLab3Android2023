package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class MisNotasView implements Serializable {

    private String descripcion;
    private double nota;
    private String fecha;




    public MisNotasView() {
    }

    public MisNotasView(String descripcion,
                        double nota,
                        String fecha

                         ) {
        this.descripcion = descripcion;
        this.nota = nota;
        this.fecha = fecha;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
