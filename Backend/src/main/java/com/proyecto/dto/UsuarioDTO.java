package com.proyecto.dto;

public class UsuarioDTO {
    private Integer idUsuario;
    private String usuario;
    private String senhia;
    private Integer cliente;

    // Getters y setters
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getSenhia() {
        return senhia;
    }
    public void setSenhia(String senhia) {
        this.senhia = senhia;
    }
    public Integer getCliente() {
        return cliente;
    }
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}