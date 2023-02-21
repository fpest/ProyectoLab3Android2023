package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class CuentaCorriente implements Serializable {

    private int id;
    private String descripcion;
    private double monto;
    private Date fechahora;
    private int personaId;


    public CuentaCorriente() {
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public CuentaCorriente(int id,
                           String descripcion,
                           double monto,
                           Date fechahora,
                           int personaId) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechahora = fechahora;
        this.personaId = personaId;

    }
}
