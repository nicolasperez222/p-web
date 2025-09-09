package com.proyecto.mapper;

import com.proyecto.dto.MensajeClienteDTO;
import com.proyecto.model.MensajeCliente;
import com.proyecto.model.Mensaje;
import com.proyecto.model.Cliente;

public class MensajeClienteMapper {
    public static MensajeClienteDTO toDTO(MensajeCliente model) {
        if (model == null) return null;
        MensajeClienteDTO dto = new MensajeClienteDTO();
        dto.setId(model.getId());
        dto.setMensajeId(model.getMensaje() != null ? model.getMensaje().getId() : null);
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setFechaLlegada(model.getFechaLlegada());
        dto.setFechaLectura(model.getFechaLectura());
        dto.setFechaEliminacion(model.getFechaEliminacion());
        dto.setEsEliminado(model.getEsEliminado());
        return dto;
    }

    public static MensajeCliente toModel(MensajeClienteDTO dto, Mensaje mensaje, Cliente cliente) {
        if (dto == null) return null;
        MensajeCliente model = new MensajeCliente();
        model.setId(dto.getId());
        model.setMensaje(mensaje);
        model.setCliente(cliente);
        model.setFechaLlegada(dto.getFechaLlegada());
        model.setFechaLectura(dto.getFechaLectura());
        model.setFechaEliminacion(dto.getFechaEliminacion());
        model.setEsEliminado(dto.getEsEliminado());
        return model;
    }
}