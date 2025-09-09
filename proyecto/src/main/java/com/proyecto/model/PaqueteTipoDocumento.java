package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paquetes_tipos_documento")
public class PaqueteTipoDocumento implements Serializable {

    @EmbeddedId
    private PaqueteTipoDocumentoId id;


    public PaqueteTipoDocumentoId getId() {
        return id;
    }

    public void setId(PaqueteTipoDocumentoId id) {
        this.id = id;
    }
}