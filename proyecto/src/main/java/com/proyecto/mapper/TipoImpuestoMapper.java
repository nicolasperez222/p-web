package com.proyecto.mapper;

import com.proyecto.dto.TipoImpuestoDTO;
import com.proyecto.model.TipoImpuesto;

public class TipoImpuestoMapper {
    public static TipoImpuestoDTO toDTO(TipoImpuesto model) {
        if (model == null) return null;
        TipoImpuestoDTO dto = new TipoImpuestoDTO();
        dto.setIdTipoImpuesto(model.getIdTipoImpuesto());
        dto.setDescripcion(model.getDescripcion());
        dto.setCode(model.getCode());
        return dto;
    }

    public static TipoImpuesto toModel(TipoImpuestoDTO dto) {
        if (dto == null) return null;
        TipoImpuesto model = new TipoImpuesto();
        model.setIdTipoImpuesto(dto.getIdTipoImpuesto());
        model.setDescripcion(dto.getDescripcion());
        model.setCode(dto.getCode());
        return model;
    }
}