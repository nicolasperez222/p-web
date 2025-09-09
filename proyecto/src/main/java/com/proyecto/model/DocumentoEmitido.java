package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "documentos_emitidos")
public class DocumentoEmitido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "numero", length = 70)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id_tipo_documento", foreignKey = @ForeignKey(name = "FK__tipos_documento_de"))
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "resolucion", referencedColumnName = "id_resolucion", foreignKey = @ForeignKey(name = "FK_documentos_emitidos_resoluciones"))
    private Resolucion resolucion;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "valor_bruto", precision = 20, scale = 6)
    private BigDecimal valorBruto;

    @Column(name = "descuentos", precision = 20, scale = 6)
    private BigDecimal descuentos;

    @Column(name = "subtotal", precision = 20, scale = 6)
    private BigDecimal subtotal;

    @Column(name = "impuestos", precision = 20, scale = 6)
    private BigDecimal impuestos;

    @Column(name = "total", precision = 20, scale = 6)
    private BigDecimal total;

    @Column(name = "cufe_cude", length = 150)
    private String cufeCude;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id_cliente", foreignKey = @ForeignKey(name = "FK__clientes_de"))
    private Cliente cliente;

    @Column(name = "json_peticion", columnDefinition = "LONGTEXT")
    private String jsonPeticion;



    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Resolucion getResolucion() {
        return resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(BigDecimal descuentos) {
        this.descuentos = descuentos;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCufeCude() {
        return cufeCude;
    }

    public void setCufeCude(String cufeCude) {
        this.cufeCude = cufeCude;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getJsonPeticion() {
        return jsonPeticion;
    }

    public void setJsonPeticion(String jsonPeticion) {
        this.jsonPeticion = jsonPeticion;
    }
}