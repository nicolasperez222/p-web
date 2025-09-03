package co.edu.javeriana.ejemplojpa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mensaje_cliente")
public class MensajeCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_mensaje", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_mensaje_cliente_mensaje"))
    private Mensaje mensaje;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK_mensaje_cliente_cliente"))
    private Cliente cliente;

    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLlegada;

    @Column(name = "fecha_lectura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLectura;

    @Column(name = "fecha_eliminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;

    @Column(name = "es_eliminado")
    private Boolean esEliminado;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public Boolean getEsEliminado() {
        return esEliminado;
    }

    public void setEsEliminado(Boolean esEliminado) {
        this.esEliminado = esEliminado;
    }
}