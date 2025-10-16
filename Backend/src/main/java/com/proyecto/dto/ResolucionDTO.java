package com.proyecto.dto;

import java.util.Date;

public class ResolucionDTO {
    private Integer idResolucion;
    private Integer clienteId;
    private Integer tipoDocumentoId;
    private String prefijo;
    private String numeroResolucion;
    private Date fechaCreacion;
    private String llaveTecnica;
    private Integer desde;
    private Integer hasta;
    private Date fechaDesde;
    private Date fechaHasta;
    public Integer getIdResolucion() {
        return idResolucion;
    }
    public void setIdResolucion(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    public Integer getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    public void setTipoDocumentoId(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    public String getPrefijo() {
        return prefijo;
    }
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
    public String getNumeroResolucion() {
        return numeroResolucion;
    }
    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getLlaveTecnica() {
        return llaveTecnica;
    }
    public void setLlaveTecnica(String llaveTecnica) {
        this.llaveTecnica = llaveTecnica;
    }
    public Integer getDesde() {
        return desde;
    }
    public void setDesde(Integer desde) {
        this.desde = desde;
    }
    public Integer getHasta() {
        return hasta;
    }
    public void setHasta(Integer hasta) {
        this.hasta = hasta;
    }
    public Date getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    public Date getFechaHasta() {
        return fechaHasta;
    }
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

}