package com.proyecto.mapper;

import com.proyecto.dto.TokenDTO;
import com.proyecto.model.Token;
import com.proyecto.model.Cliente;

public class TokenMapper {
    public static TokenDTO toDTO(Token model) {
        if (model == null) return null;
        TokenDTO dto = new TokenDTO();
        dto.setIdToken(model.getIdToken());
        dto.setToken(model.getToken());
        dto.setExpiracion(model.getExpiracion());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        return dto;
    }

    public static Token toModel(TokenDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Token model = new Token();
        model.setIdToken(dto.getIdToken());
        model.setToken(dto.getToken());
        model.setExpiracion(dto.getExpiracion());
        model.setCliente(cliente);
        return model;
    }
}
