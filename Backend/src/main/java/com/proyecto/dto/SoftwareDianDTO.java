package com.proyecto.dto;

public class SoftwareDianDTO {
    private Integer idSoftware;
    private String idSoftwareFacturacion;
    private String pinFacturacion;
    private String idSoftwareEpos;
    private String pinEpos;
    private Integer clienteId;
    private Integer ambienteFe;
    private Integer ambienteEpos;

    public Integer getIdSoftware() {
        return idSoftware;
    }
    
    public void setIdSoftware(Integer idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getIdSoftwareFacturacion() {
        return idSoftwareFacturacion;
    }

    public void setIdSoftwareFacturacion(String idSoftwareFacturacion) {
        this.idSoftwareFacturacion = idSoftwareFacturacion;
    }

    public String getPinFacturacion() {
        return pinFacturacion;
    }

    public void setPinFacturacion(String pinFacturacion) {
        this.pinFacturacion = pinFacturacion;
    }

    public String getIdSoftwareEpos() {
        return idSoftwareEpos;
    }

    public void setIdSoftwareEpos(String idSoftwareEpos) {
        this.idSoftwareEpos = idSoftwareEpos;
    }

    public String getPinEpos() {
        return pinEpos;
    }

    public void setPinEpos(String pinEpos) {
        this.pinEpos = pinEpos;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getAmbienteFe() {
        return ambienteFe;
    }

    public void setAmbienteFe(Integer ambienteFe) {
        this.ambienteFe = ambienteFe;
    }

    public Integer getAmbienteEpos() {
        return ambienteEpos;
    }

    public void setAmbienteEpos(Integer ambienteEpos) {
        this.ambienteEpos = ambienteEpos;
    }

}