package com.proyecto.dto;

import java.util.Date;

public class MensajeClienteDTO {
    private Integer id;
    private Integer mensajeId;
    private Integer clienteId;
    private Date fechaLlegada;
    private Date fechaLectura;
    private Date fechaEliminacion;
    private Boolean esEliminado;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMensajeId() {
        return mensajeId;
    }
    public void setMensajeId(Integer mensajeId) {
        this.mensajeId = mensajeId;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    public Date getFechaLlegada() {
        return fechaLlegada;
    }
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
    public Date getFechaLectura() {
        return fechaLectura;
    }
    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }
    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }
    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }
    public Boolean getEsEliminado() {
        return esEliminado;
    }
    public void setEsEliminado(Boolean esEliminado) {
        this.esEliminado = esEliminado;
    }

    
}