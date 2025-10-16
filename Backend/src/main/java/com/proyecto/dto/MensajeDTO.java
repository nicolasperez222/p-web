package com.proyecto.dto;

import java.util.Date;

public class MensajeDTO {
    private Integer id;
    private String mensaje;
    private String deUsuario;
    private String asunto;
    private String html;
    private Boolean esHtml;
    private Date fechaEmision;
    private Date fechaExpiracion;
    private String prioridad;
    private String tipoMensaje;
    private Boolean esUrgente;
    private String adjuntoUrl;
    private String creadoPor;
    private String checksumContenido;
    private Boolean esClienteEspecifico;
    private Integer clienteId;
    private Boolean esEliminado;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getDeUsuario() {
        return deUsuario;
    }
    public void setDeUsuario(String deUsuario) {
        this.deUsuario = deUsuario;
    }
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getHtml() {
        return html;
    }
    public void setHtml(String html) {
        this.html = html;
    }
    public Boolean getEsHtml() {
        return esHtml;
    }
    public void setEsHtml(Boolean esHtml) {
        this.esHtml = esHtml;
    }
    public Date getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public String getTipoMensaje() {
        return tipoMensaje;
    }
    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }
    public Boolean getEsUrgente() {
        return esUrgente;
    }
    public void setEsUrgente(Boolean esUrgente) {
        this.esUrgente = esUrgente;
    }
    public String getAdjuntoUrl() {
        return adjuntoUrl;
    }
    public void setAdjuntoUrl(String adjuntoUrl) {
        this.adjuntoUrl = adjuntoUrl;
    }
    public String getCreadoPor() {
        return creadoPor;
    }
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
    public String getChecksumContenido() {
        return checksumContenido;
    }
    public void setChecksumContenido(String checksumContenido) {
        this.checksumContenido = checksumContenido;
    }
    public Boolean getEsClienteEspecifico() {
        return esClienteEspecifico;
    }
    public void setEsClienteEspecifico(Boolean esClienteEspecifico) {
        this.esClienteEspecifico = esClienteEspecifico;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    public Boolean getEsEliminado() {
        return esEliminado;
    }
    public void setEsEliminado(Boolean esEliminado) {
        this.esEliminado = esEliminado;
    }

    
}