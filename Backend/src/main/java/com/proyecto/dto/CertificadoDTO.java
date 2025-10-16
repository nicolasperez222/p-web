package com.proyecto.dto;

import java.util.Date;

public class CertificadoDTO {
    private Integer idCertificado;
    private String nombreArchivo;
    private String data;
    private Integer vigenciaAnios;
    private Date vencimiento;
    private Date fechaRegistro;
    private Integer clienteId;
    
    public CertificadoDTO() {
    }
    
    public Integer getIdCertificado() {
        return idCertificado;
    }
    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Integer getVigenciaAnios() {
        return vigenciaAnios;
    }
    public void setVigenciaAnios(Integer vigenciaAnios) {
        this.vigenciaAnios = vigenciaAnios;
    }
    public Date getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

}