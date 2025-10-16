package com.proyecto.mapper;

import com.proyecto.dto.MunicipioDTO;
import com.proyecto.model.Municipio;

public class MunicipioMapper {

    public static MunicipioDTO toDTO(Municipio model) {
        if (model == null) return null;
        MunicipioDTO dto = new MunicipioDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setCodigo(model.getCodigo());
        return dto;
    }

    public static Municipio toModel(MunicipioDTO dto) {
        if (dto == null) return null;
        Municipio model = new Municipio();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setCodigo(dto.getCodigo());
        return model;
    }
}