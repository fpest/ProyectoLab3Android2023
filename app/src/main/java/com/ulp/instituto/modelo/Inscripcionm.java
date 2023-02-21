package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class Inscripcionm implements Serializable {

    private int id;
    private int personaId;
    private int materiaId;
    private Date fechahora;
    private String estado;


    public Inscripcionm() {
    }

    public Inscripcionm(int id,
                        int personaId,
                        int materiaId,
                        Date fechahora,
                        String estado) {
        this.id = id;
        this.personaId = personaId;
        this.materiaId = materiaId;
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

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
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
