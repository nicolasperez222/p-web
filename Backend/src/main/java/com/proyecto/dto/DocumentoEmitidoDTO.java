package com.proyecto.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentoEmitidoDTO {
    private Integer idDocumento;
    private String numero;
    private Integer tipoDocumentoId;
    private Integer resolucionId;
    private Date fecha;
    private BigDecimal valorBruto;
    private BigDecimal descuentos;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String cufeCude;
    private Integer clienteId;
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
    public Integer getTipoDocumentoId() {
        return tipoDocumentoId;
    }
    public void setTipoDocumentoId(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
    public Integer getResolucionId() {
        return resolucionId;
    }
    public void setResolucionId(Integer resolucionId) {
        this.resolucionId = resolucionId;
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
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
    public String getJsonPeticion() {
        return jsonPeticion;
    }
    public void setJsonPeticion(String jsonPeticion) {
        this.jsonPeticion = jsonPeticion;
    }

}