package com.ulp.instituto.modelo;

import java.io.Serializable;

public class MisCuentasView implements Serializable {

    private String descripcion;
    private double monto;
    private String fechahora;




    public MisCuentasView() {
    }

    public MisCuentasView(String descripcion,
                          double monto,
                          String fechahora

                         ) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechahora = fechahora;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double nota) {
        this.monto = monto;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }
}
