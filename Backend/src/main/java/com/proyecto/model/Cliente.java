package com.proyecto.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @Column(name = "representante_legal", length = 100)
    private String representanteLegal;

    @Column(name = "nit", length = 15)
    private String nit;

    @Column(name = "dv", length = 2)
    private String dv;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;

    @Column(name = "tipo_empresa")
    private Integer tipoEmpresa;

    @Column(name = "responsabilidad_tributaria", length = 50)
    private String responsabilidadTributaria;

    @Column(name = "regimen_iva", length = 100)
    private String regimenIva;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "codigo_ciiu", length = 45)
    private String codigoCiiu;

    @Column(name = "impuesto", length = 50)
    private String impuesto;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio")
    private Municipio municipio;

    @Lob
    @Column(name = "logo")
    private String logo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(Integer tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getResponsabilidadTributaria() {
        return responsabilidadTributaria;
    }

    public void setResponsabilidadTributaria(String responsabilidadTributaria) {
        this.responsabilidadTributaria = responsabilidadTributaria;
    }

    public String getRegimenIva() {
        return regimenIva;
    }

    public void setRegimenIva(String regimenIva) {
        this.regimenIva = regimenIva;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoCiiu() {
        return codigoCiiu;
    }

    public void setCodigoCiiu(String codigoCiiu) {
        this.codigoCiiu = codigoCiiu;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getIdCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdCliente'");
    }
}