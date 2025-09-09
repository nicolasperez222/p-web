package com.proyecto.mapper;

import com.proyecto.dto.ClienteDTO;
import com.proyecto.model.Cliente;
import com.proyecto.model.Departamento;
import com.proyecto.model.Municipio;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente model) {
        if (model == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setId(model.getId());
        dto.setRepresentanteLegal(model.getRepresentanteLegal());
        dto.setNit(model.getNit());
        dto.setDv(model.getDv());
        dto.setRazonSocial(model.getRazonSocial());
        dto.setTipoEmpresa(model.getTipoEmpresa());
        dto.setResponsabilidadTributaria(model.getResponsabilidadTributaria());
        dto.setRegimenIva(model.getRegimenIva());
        dto.setDireccion(model.getDireccion());
        dto.setEmail(model.getEmail());
        dto.setTelefono(model.getTelefono());
        dto.setCodigoCiiu(model.getCodigoCiiu());
        dto.setImpuesto(model.getImpuesto());
        dto.setEstado(model.getEstado());
        dto.setDepartamentoId(model.getDepartamento() != null ? model.getDepartamento().getId() : null);
        dto.setMunicipioId(model.getMunicipio() != null ? model.getMunicipio().getId() : null);
        dto.setLogo(model.getLogo());
        return dto;
    }

    public static Cliente toModel(ClienteDTO dto, Departamento departamento, Municipio municipio) {
        if (dto == null) return null;
        Cliente model = new Cliente();
        model.setId(dto.getId());
        model.setRepresentanteLegal(dto.getRepresentanteLegal());
        model.setNit(dto.getNit());
        model.setDv(dto.getDv());
        model.setRazonSocial(dto.getRazonSocial());
        model.setTipoEmpresa(dto.getTipoEmpresa());
        model.setResponsabilidadTributaria(dto.getResponsabilidadTributaria());
        model.setRegimenIva(dto.getRegimenIva());
        model.setDireccion(dto.getDireccion());
        model.setEmail(dto.getEmail());
        model.setTelefono(dto.getTelefono());
        model.setCodigoCiiu(dto.getCodigoCiiu());
        model.setImpuesto(dto.getImpuesto());
        model.setEstado(dto.getEstado());
        model.setDepartamento(departamento);
        model.setMunicipio(municipio);
        model.setLogo(dto.getLogo());
        return model;
    }
}