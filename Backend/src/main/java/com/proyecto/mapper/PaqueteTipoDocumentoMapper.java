package com.proyecto.mapper;

import com.proyecto.dto.PaqueteTipoDocumentoDTO;
import com.proyecto.model.PaqueteTipoDocumento;
public class PaqueteTipoDocumentoMapper {
    public static PaqueteTipoDocumentoDTO toDTO(PaqueteTipoDocumento model) {
        if (model == null) return null;
        PaqueteTipoDocumentoDTO dto = new PaqueteTipoDocumentoDTO();
        dto.setPaqueteId(model.getPaqueteId());
        dto.setTipoDocumentoId(model.getTipoDocumentoId());
        return dto;
    }

    public static PaqueteTipoDocumento toModel(PaqueteTipoDocumentoDTO dto) {
        if (dto == null) return null;
        PaqueteTipoDocumento model = new PaqueteTipoDocumento();
        model.setPaqueteId(dto.getPaqueteId());
        model.setTipoDocumentoId(dto.getTipoDocumentoId());
        return model;
    }
}