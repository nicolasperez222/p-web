package co.edu.javeriana.ejemplojpa.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "referencias_pago")
public class ReferenciaPago implements Serializable {

    @Id
    @Column(name = "id_referencia")
    private Integer idReferencia;

    @ManyToOne
    @JoinColumn(name = "id_software", referencedColumnName = "id_software", foreignKey = @ForeignKey(name = "FK_referencias_pago_software_dian"))
    private SoftwareDian softwareDian;

    @Column(name = "referencia", length = 50)
    private String referencia;

    // Getters y setters

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    public SoftwareDian getSoftwareDian() {
        return softwareDian;
    }

    public void setSoftwareDian(SoftwareDian softwareDian) {
        this.softwareDian = softwareDian;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}