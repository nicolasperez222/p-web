package com.proyecto.mapper;

import com.proyecto.dto.PaqueteWebDTO;
import com.proyecto.model.PaqueteWeb;

public class PaqueteWebMapper {
    public static PaqueteWebDTO toDTO(PaqueteWeb model) {
        if (model == null) return null;
        PaqueteWebDTO dto = new PaqueteWebDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setPrecio(model.getPrecio());
        dto.setUrl(model.getUrl());
        return dto;
    }

    public static PaqueteWeb toModel(PaqueteWebDTO dto) {
        if (dto == null) return null;
        PaqueteWeb model = new PaqueteWeb();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setPrecio(dto.getPrecio());
        model.setUrl(dto.getUrl());
        return model;
    }
}