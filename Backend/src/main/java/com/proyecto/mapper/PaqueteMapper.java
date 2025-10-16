package com.proyecto.mapper;

import com.proyecto.dto.PaqueteDTO;
import com.proyecto.model.Paquete;
import com.proyecto.model.Cliente;

public class PaqueteMapper {
    public static PaqueteDTO toDTO(Paquete model) {
        if (model == null) return null;
        PaqueteDTO dto = new PaqueteDTO();
        dto.setIdPaquete(model.getIdPaquete());
        dto.setFechaCreacion(model.getFechaCreacion());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setDocumentosIniciales(model.getDocumentosIniciales());
        dto.setFechaPlan(model.getFechaPlan());
        dto.setFechaFin(model.getFechaFin());
        dto.setPeriodo(model.getPeriodo());
        dto.setDocumentosRestante(model.getDocumentosRestante());
        dto.setEstado(model.getEstado());
        dto.setTransaccion(model.getTransaccion());
        return dto;
    }

    public static Paquete toModel(PaqueteDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Paquete model = new Paquete();
        model.setIdPaquete(dto.getIdPaquete());
        model.setFechaCreacion(dto.getFechaCreacion());
        model.setCliente(cliente);
        model.setDocumentosIniciales(dto.getDocumentosIniciales());
        model.setFechaPlan(dto.getFechaPlan());
        model.setFechaFin(dto.getFechaFin());
        model.setPeriodo(dto.getPeriodo());
        model.setDocumentosRestante(dto.getDocumentosRestante());
        model.setEstado(dto.getEstado());
        model.setTransaccion(dto.getTransaccion());
        return model;
    }
}