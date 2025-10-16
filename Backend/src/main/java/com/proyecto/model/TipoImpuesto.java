package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipos_impuesto")
public class TipoImpuesto implements Serializable {

    @Id
    @Column(name = "id_tipo_impuesto")
    private Integer idTipoImpuesto;

    @Column(name = "descripcion", length = 50, nullable = false)
    private String descripcion;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    // Getters y setters

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