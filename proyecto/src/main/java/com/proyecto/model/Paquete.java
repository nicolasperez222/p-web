package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "paquetes")
public class Paquete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private Integer idPaquete;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK__clientes_paq"))
    private Cliente cliente;

    @Column(name = "documentos_iniciales")
    private Integer documentosIniciales;

    @Column(name = "fecha_plan")
    @Temporal(TemporalType.DATE)
    private Date fechaPlan;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "periodo", length = 50)
    private String periodo;

    @Column(name = "documentos_restante")
    private Integer documentosRestante;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "transaccion", length = 200)
    private String transaccion;

    // Getters y setters

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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