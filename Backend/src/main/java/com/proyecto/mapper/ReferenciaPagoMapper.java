package com.proyecto.mapper;

import com.proyecto.dto.ReferenciaPagoDTO;
import com.proyecto.model.ReferenciaPago;
import com.proyecto.model.SoftwareDian;

public class ReferenciaPagoMapper {
    public static ReferenciaPagoDTO toDTO(ReferenciaPago model) {
        if (model == null) return null;
        ReferenciaPagoDTO dto = new ReferenciaPagoDTO();
        dto.setIdReferencia(model.getIdReferencia());
        dto.setSoftwareDianId(model.getSoftwareDian() != null ? model.getSoftwareDian().getIdSoftware() : null);
        dto.setReferencia(model.getReferencia());
        return dto;
    }

    public static ReferenciaPago toModel(ReferenciaPagoDTO dto, SoftwareDian softwareDian) {
        if (dto == null) return null;
        ReferenciaPago model = new ReferenciaPago();
        model.setIdReferencia(dto.getIdReferencia());
        model.setSoftwareDian(softwareDian);
        model.setReferencia(dto.getReferencia());
        return model;
    }
}