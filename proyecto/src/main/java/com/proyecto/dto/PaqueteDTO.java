package com.proyecto.dto;

import java.util.Date;

public class PaqueteDTO {
    private Integer idPaquete;
    private Date fechaCreacion;
    private Integer clienteId;
    private Integer documentosIniciales;
    private Date fechaPlan;
    private Date fechaFin;
    private String periodo;
    private Integer documentosRestante;
    private Integer estado;
    private String transaccion;
    
    public Integer getIdPaquete() {
        return idPaquete;
    }
    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    public Integer getDocumentosIniciales() {
        return documentosIniciales;
    }
    public void setDocumentosIniciales(Integer documentosIniciales) {
        this.documentosIniciales = documentosIniciales;
    }
    public Date getFechaPlan() {
        return fechaPlan;
    }
    public void setFechaPlan(Date fechaPlan) {
        this.fechaPlan = fechaPlan;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public Integer getDocumentosRestante() {
        return documentosRestante;
    }
    public void setDocumentosRestante(Integer documentosRestante) {
        this.documentosRestante = documentosRestante;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public String getTransaccion() {
        return transaccion;
    }
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

   
}