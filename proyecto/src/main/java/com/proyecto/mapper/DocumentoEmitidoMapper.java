package com.proyecto.mapper;

import com.proyecto.dto.DocumentoEmitidoDTO;
import com.proyecto.model.DocumentoEmitido;
import com.proyecto.model.TipoDocumento;
import com.proyecto.model.Resolucion;
import com.proyecto.model.Cliente;

public class DocumentoEmitidoMapper {
    public static DocumentoEmitidoDTO toDTO(DocumentoEmitido model) {
        if (model == null) return null;
        DocumentoEmitidoDTO dto = new DocumentoEmitidoDTO();
        dto.setIdDocumento(model.getIdDocumento());
        dto.setNumero(model.getNumero());
        dto.setTipoDocumentoId(model.getTipoDocumento() != null ? model.getTipoDocumento().getIdTipoDocumento() : null);
        dto.setResolucionId(model.getResolucion() != null ? model.getResolucion().getIdResolucion() : null);
        dto.setFecha(model.getFecha());
        dto.setValorBruto(model.getValorBruto());
        dto.setDescuentos(model.getDescuentos());
        dto.setSubtotal(model.getSubtotal());
        dto.setImpuestos(model.getImpuestos());
        dto.setTotal(model.getTotal());
        dto.setCufeCude(model.getCufeCude());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setJsonPeticion(model.getJsonPeticion());
        return dto;
    }

    public static DocumentoEmitido toModel(DocumentoEmitidoDTO dto, TipoDocumento tipoDocumento, Resolucion resolucion, Cliente cliente) {
        if (dto == null) return null;
        DocumentoEmitido model = new DocumentoEmitido();
        model.setIdDocumento(dto.getIdDocumento());
        model.setNumero(dto.getNumero());
        model.setTipoDocumento(tipoDocumento);
        model.setResolucion(resolucion);
        model.setFecha(dto.getFecha());
        model.setValorBruto(dto.getValorBruto());
        model.setDescuentos(dto.getDescuentos());
        model.setSubtotal(dto.getSubtotal());
        model.setImpuestos(dto.getImpuestos());
        model.setTotal(dto.getTotal());
        model.setCufeCude(dto.getCufeCude());
        model.setCliente(cliente);
        model.setJsonPeticion(dto.getJsonPeticion());
        return model;
    }
}