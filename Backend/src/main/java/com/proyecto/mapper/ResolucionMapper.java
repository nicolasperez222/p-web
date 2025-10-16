package com.proyecto.mapper;

import com.proyecto.dto.ResolucionDTO;
import com.proyecto.model.Resolucion;
import com.proyecto.model.Cliente;
import com.proyecto.model.TipoDocumento;

public class ResolucionMapper {

    public static ResolucionDTO toDTO(Resolucion model) {
        if (model == null) return null;

        ResolucionDTO dto = new ResolucionDTO();
        dto.setIdResolucion(model.getIdResolucion());
        dto.setClienteId(model.getCliente() != null ? model.getCliente().getId() : null);
        dto.setTipoDocumentoId(model.getTipoDocumento() != null ? model.getTipoDocumento().getIdTipoDocumento() : null);
        dto.setPrefijo(model.getPrefijo());
        dto.setNumeroResolucion(model.getNumeroResolucion());
        dto.setFechaCreacion(model.getFechaCreacion());
        dto.setLlaveTecnica(model.getLlaveTecnica());
        dto.setDesde(model.getDesde());
        dto.setHasta(model.getHasta());
        dto.setFechaDesde(model.getFechaDesde());
        dto.setFechaHasta(model.getFechaHasta());
        return dto;
    }

    public static Resolucion toModel(ResolucionDTO dto, Cliente cliente, TipoDocumento tipoDocumento) {
        if (dto == null) return null;

        Resolucion model = new Resolucion();
        model.setIdResolucion(dto.getIdResolucion());

        if (cliente != null) {
            model.setCliente(cliente);
        }

        if (tipoDocumento != null) {
            model.setTipoDocumento(tipoDocumento);
        }

        model.setPrefijo(dto.getPrefijo());
        model.setNumeroResolucion(dto.getNumeroResolucion());
        model.setFechaCreacion(dto.getFechaCreacion());
        model.setLlaveTecnica(dto.getLlaveTecnica());
        model.setDesde(dto.getDesde());
        model.setHasta(dto.getHasta());
        model.setFechaDesde(dto.getFechaDesde());
        model.setFechaHasta(dto.getFechaHasta());

        return model;
    }
}
