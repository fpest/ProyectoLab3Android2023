package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class Inscripcionc implements Serializable {

    private int id;
    private int personaId;
    private int carreraId;
    private Date fechahora;
    private String estado;


    public Inscripcionc() {
    }

    public Inscripcionc(int id,
                        int personaId,
                        int carreraId,
                        Date fechahora,
                        String estado) {
        this.id = id;
        this.personaId = personaId;
        this.carreraId = carreraId;
        this.fechahora = fechahora;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
