package com.proyecto.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PaqueteTipoDocumentoId implements Serializable {

    private Integer paqueteId;
    private Integer tipoDocumentoId;

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

    // Sobrescribir equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaqueteTipoDocumentoId that = (PaqueteTipoDocumentoId) o;
        return Objects.equals(paqueteId, that.paqueteId) &&
               Objects.equals(tipoDocumentoId, that.tipoDocumentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paqueteId, tipoDocumentoId);
    }
}