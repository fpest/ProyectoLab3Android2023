package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class Registronotas implements Serializable {

    private int id;
    private double nota;
    private Date fecha;
    private int inscripcionmId;



    public Registronotas() {
    }

    public Registronotas(int id,
                         double nota,
                         Date fecha,
                         int inscripcionmId
                         ) {
        this.id = id;
        this.nota = nota;
        this.fecha = fecha;
        this.inscripcionmId = inscripcionmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getInscripcionmId() {
        return inscripcionmId;
    }

    public void setInscripcionmId(int inscripcionmId) {
        this.inscripcionmId = inscripcionmId;
    }
}
