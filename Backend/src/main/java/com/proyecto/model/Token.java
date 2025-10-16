package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Integer idToken;

    @Column(name = "token", length = 150)
    private String token;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK__clientes"))
    private Cliente cliente;

    @Column(name = "expiracion")
    @Temporal(TemporalType.DATE)
    private Date expiracion;

    // Getters y setters

    public Integer getIdToken() {
        return idToken;
    }

    public void setIdToken(Integer idToken) {
        this.idToken = idToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
        this.expiracion = expiracion;
    }
}