package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "software_dian")
public class SoftwareDian implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_software")
    private Integer idSoftware;

    @Column(name = "id_software_facturacion", length = 150)
    private String idSoftwareFacturacion;

    @Column(name = "pin_facturacion", length = 10)
    private String pinFacturacion;

    @Column(name = "id_software_epos", length = 150)
    private String idSoftwareEpos;

    @Column(name = "pin_epos", length = 10)
    private String pinEpos;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK__clientes_software"))
    private Cliente cliente;

    @Column(name = "ambiente_fe")
    private Integer ambienteFe;

    @Column(name = "ambiente_epos")
    private Integer ambienteEpos;

    // Getters y setters

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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