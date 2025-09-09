package com.proyecto.mapper;

import com.proyecto.dto.UsuarioDTO;
import com.proyecto.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario model) {
        if (model == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(model.getIdUsuario());
        dto.setUsuario(model.getUsuario());
        dto.setSenhia(model.getSenhia());
        dto.setCliente(model.getCliente());
        return dto;
    }

    public static Usuario toModel(UsuarioDTO dto) {
        if (dto == null) return null;
        Usuario model = new Usuario();
        model.setIdUsuario(dto.getIdUsuario());
        model.setUsuario(dto.getUsuario());
        model.setSenhia(dto.getSenhia());
        model.setCliente(dto.getCliente());
        return model;
    }
}