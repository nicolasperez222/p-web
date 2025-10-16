package com.proyecto.mapper;

import com.proyecto.dto.SoftwareDianDTO;
import com.proyecto.model.SoftwareDian;
import com.proyecto.model.Cliente;

public class SoftwareDianMapper {
    public static SoftwareDianDTO toDTO(SoftwareDian model) {
        if (model == null) return null;
        SoftwareDianDTO dto = new SoftwareDianDTO();
        dto.setIdSoftware(model.getIdSoftware());
        dto.setIdSoftwareFacturacion(model.getIdSoftwareFacturacion());
        dto.setPinFacturacion(model.getPinFacturacion());
        dto.setIdSoftwareEpos(model.getIdSoftwareEpos());
        dto.setPinEpos(model.getPinEpos());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setAmbienteFe(model.getAmbienteFe());
        dto.setAmbienteEpos(model.getAmbienteEpos());
        return dto;
    }

    public static SoftwareDian toModel(SoftwareDianDTO dto, Cliente cliente) {
        if (dto == null) return null;
        SoftwareDian model = new SoftwareDian();
        model.setIdSoftware(dto.getIdSoftware());
        model.setIdSoftwareFacturacion(dto.getIdSoftwareFacturacion());
        model.setPinFacturacion(dto.getPinFacturacion());
        model.setIdSoftwareEpos(dto.getIdSoftwareEpos());
        model.setPinEpos(dto.getPinEpos());
        model.setCliente(cliente);
        model.setAmbienteFe(dto.getAmbienteFe());
        model.setAmbienteEpos(dto.getAmbienteEpos());
        return model;
    }
}