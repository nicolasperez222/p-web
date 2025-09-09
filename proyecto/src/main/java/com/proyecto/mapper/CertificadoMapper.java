package com.proyecto.mapper;

import com.proyecto.dto.CertificadoDTO;
import com.proyecto.model.Certificado;
import com.proyecto.model.Cliente;

public class CertificadoMapper {
    public static CertificadoDTO toDTO(Certificado model) {
        if (model == null) return null;
        CertificadoDTO dto = new CertificadoDTO();
        dto.setIdCertificado(model.getIdCertificado());
        dto.setNombreArchivo(model.getNombreArchivo());
        dto.setData(model.getData());
        dto.setVigenciaAnios(model.getVigenciaAnios());
        dto.setVencimiento(model.getVencimiento());
        dto.setFechaRegistro(model.getFechaRegistro());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        return dto;
    }

    public static Certificado toModel(CertificadoDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Certificado model = new Certificado();
        model.setIdCertificado(dto.getIdCertificado());
        model.setNombreArchivo(dto.getNombreArchivo());
        model.setData(dto.getData());
        model.setVigenciaAnios(dto.getVigenciaAnios());
        model.setVencimiento(dto.getVencimiento());
        model.setFechaRegistro(dto.getFechaRegistro());
        model.setCliente(cliente);
        return model;
    }
}