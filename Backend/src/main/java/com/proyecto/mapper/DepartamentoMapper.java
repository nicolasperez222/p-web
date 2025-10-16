package com.proyecto.mapper;

import com.proyecto.dto.DepartamentoDTO;
import com.proyecto.model.Departamento;

public class DepartamentoMapper {
    public static DepartamentoDTO toDTO(Departamento model) {
        if (model == null) return null;
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        return dto;
    }

    public static Departamento toModel(DepartamentoDTO dto) {
        if (dto == null) return null;
        Departamento model = new Departamento();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        return model;
    }
}