package com.proyecto.dto;


public class ReferenciaPagoDTO {
    private Integer idReferencia;
    private Integer softwareDianId;
    private String referencia;

    public Integer getIdReferencia() {
        return idReferencia;
    }
    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }
    public Integer getSoftwareDianId() {
        return softwareDianId;
    }
    public void setSoftwareDianId(Integer softwareDianId) {
        this.softwareDianId = softwareDianId;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
}