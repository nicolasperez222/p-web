package com.proyecto.mapper;

import com.proyecto.dto.TipoDocumentoDTO;
import com.proyecto.model.TipoDocumento;

public class TipoDocumentoMapper {
    public static TipoDocumentoDTO toDTO(TipoDocumento model) {
        if (model == null) return null;
        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setIdTipoDocumento(model.getIdTipoDocumento());
        dto.setNombre(model.getNombre());
        dto.setCodigo(model.getCodigo());
        return dto;
    }

    public static TipoDocumento toModel(TipoDocumentoDTO dto) {
        if (dto == null) return null;
        TipoDocumento model = new TipoDocumento();
        model.setIdTipoDocumento(dto.getIdTipoDocumento());
        model.setNombre(dto.getNombre());
        model.setCodigo(dto.getCodigo());
        return model;
    }
}