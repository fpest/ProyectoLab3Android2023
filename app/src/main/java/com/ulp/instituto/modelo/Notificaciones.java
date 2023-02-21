package com.ulp.instituto.modelo;

import java.io.Serializable;
import java.util.Date;

public class Notificaciones implements Serializable {

    private int id;
    private int personaId;
    private Date fecha;
    private String asunto;
    private String mensaje;


    public Notificaciones() {
    }

    public Notificaciones(int id,
                          int personaId,
                          Date fecha,
                          String asunto,
                          String mensaje) {
        this.id = id;
        this.personaId = personaId;
        this.fecha = fecha;
        this.asunto = asunto;
        this.mensaje = mensaje;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
