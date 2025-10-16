package com.proyecto.dto;


public class TipoImpuestoDTO {
    private Integer idTipoImpuesto;
    private String descripcion;
    private String code;
    public Integer getIdTipoImpuesto() {
        return idTipoImpuesto;
    }
    public void setIdTipoImpuesto(Integer idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
}