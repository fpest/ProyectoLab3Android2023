package com.ulp.instituto.modelo;

import java.io.Serializable;

public class MisCuentasView implements Serializable {

    private String descripcion;
    private double monto;
    private String fechahora;
    private int id;




    public MisCuentasView() {
    }

    public MisCuentasView(String descripcion,
                          double monto,
                          String fechahora,
                          int id

                         ) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechahora = fechahora;
        this.id = id;
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

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
