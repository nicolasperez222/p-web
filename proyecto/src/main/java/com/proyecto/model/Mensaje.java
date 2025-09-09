package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mensaje", columnDefinition = "MEDIUMTEXT")
    private String mensaje;

    @Column(name = "de_usuario", length = 100)
    private String deUsuario;

    @Column(name = "asunto", columnDefinition = "MEDIUMTEXT")
    private String asunto;

    @Column(name = "html", columnDefinition = "LONGTEXT")
    private String html;

    @Column(name = "es_html")
    private Boolean esHtml;

    @Column(name = "fecha_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;

    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;

    @Column(name = "prioridad", length = 5)
    private String prioridad;

    @Column(name = "tipo_mensaje", length = 10)
    private String tipoMensaje;

    @Column(name = "es_urgente")
    private Boolean esUrgente;

    @Column(name = "adjunto_url", columnDefinition = "MEDIUMTEXT")
    private String adjuntoUrl;

    @Column(name = "creado_por", length = 100)
    private String creadoPor;

    @Column(name = "checksum_contenido", length = 64)
    private String checksumContenido;

    @Column(name = "es_cliente_especifico")
    private Boolean esClienteEspecifico;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK_mensajes_clientes"))
    private Cliente cliente;

    @Column(name = "es_eliminado")
    private Boolean esEliminado;

    // Getters y setters

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boolean getEsEliminado() {
        return esEliminado;
    }

    public void setEsEliminado(Boolean esEliminado) {
        this.esEliminado = esEliminado;
    }
}