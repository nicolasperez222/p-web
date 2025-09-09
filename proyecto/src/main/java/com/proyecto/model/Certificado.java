package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "certificados")
public class Certificado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Integer idCertificado;

    @Column(name = "nombre_archivo", length = 70)
    private String nombreArchivo;

    @Column(name = "data", length = 150)
    private String data;

    @Column(name = "vigencia_anios")
    private Integer vigenciaAnios;

    @Column(name = "vencimiento")
    @Temporal(TemporalType.DATE)
    private Date vencimiento;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK__clientes_cert"))
    private Cliente cliente;

    // Getters y setters

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}