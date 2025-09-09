package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paquetes_tipos_documento")
public class PaqueteTipoDocumento implements Serializable {

    @Id
    @Column(name = "paquete")
    private Integer paqueteId;

    @Id
    @Column(name = "tipo_documento")
    private Integer tipoDocumentoId;

    // Getters y setters

    public Integer getPaqueteId() {
        return paqueteId;
    }

    public void setPaqueteId(Integer paqueteId) {
        this.paqueteId = paqueteId;
    }

    public Integer getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
}