package com.proyecto.mapper;

import com.proyecto.dto.MensajeDTO;
import com.proyecto.model.Mensaje;
import com.proyecto.model.Cliente;

public class MensajeMapper {
    public static MensajeDTO toDTO(Mensaje model) {
        if (model == null) return null;
        MensajeDTO dto = new MensajeDTO();
        dto.setId(model.getId());
        dto.setMensaje(model.getMensaje());
        dto.setDeUsuario(model.getDeUsuario());
        dto.setAsunto(model.getAsunto());
        dto.setHtml(model.getHtml());
        dto.setEsHtml(model.getEsHtml());
        dto.setFechaEmision(model.getFechaEmision());
        dto.setFechaExpiracion(model.getFechaExpiracion());
        dto.setPrioridad(model.getPrioridad());
        dto.setTipoMensaje(model.getTipoMensaje());
        dto.setEsUrgente(model.getEsUrgente());
        dto.setAdjuntoUrl(model.getAdjuntoUrl());
        dto.setCreadoPor(model.getCreadoPor());
        dto.setChecksumContenido(model.getChecksumContenido());
        dto.setEsClienteEspecifico(model.getEsClienteEspecifico());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setEsEliminado(model.getEsEliminado());
        return dto;
    }

    public static Mensaje toModel(MensajeDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Mensaje model = new Mensaje();
        model.setId(dto.getId());
        model.setMensaje(dto.getMensaje());
        model.setDeUsuario(dto.getDeUsuario());
        model.setAsunto(dto.getAsunto());
        model.setHtml(dto.getHtml());
        model.setEsHtml(dto.getEsHtml());
        model.setFechaEmision(dto.getFechaEmision());
        model.setFechaExpiracion(dto.getFechaExpiracion());
        model.setPrioridad(dto.getPrioridad());
        model.setTipoMensaje(dto.getTipoMensaje());
        model.setEsUrgente(dto.getEsUrgente());
        model.setAdjuntoUrl(dto.getAdjuntoUrl());
        model.setCreadoPor(dto.getCreadoPor());
        model.setChecksumContenido(dto.getChecksumContenido());
        model.setEsClienteEspecifico(dto.getEsClienteEspecifico());
        model.setCliente(cliente);
        model.setEsEliminado(dto.getEsEliminado());
        return model;
    }
}