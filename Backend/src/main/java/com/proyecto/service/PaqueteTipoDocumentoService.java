package com.proyecto.service;

import com.proyecto.dto.PaqueteTipoDocumentoDTO;
import com.proyecto.mapper.PaqueteTipoDocumentoMapper;
import com.proyecto.model.PaqueteTipoDocumento;
import com.proyecto.repository.PaqueteTipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteTipoDocumentoService {

    @Autowired
    private PaqueteTipoDocumentoRepository paqueteTipoDocumentoRepository;

    public PaqueteTipoDocumentoDTO getPaqueteTipoDocumento(Integer paqueteId, Integer tipoDocumentoId) {
        return paqueteTipoDocumentoRepository.findByPaqueteIdAndTipoDocumentoId(paqueteId, tipoDocumentoId)
                .map(PaqueteTipoDocumentoMapper::toDTO)
                .orElse(null);
    }

    public List<PaqueteTipoDocumentoDTO> getPaquetesTiposDocumento() {
        return paqueteTipoDocumentoRepository.findAll()
                .stream()
                .map(PaqueteTipoDocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaqueteTipoDocumentoDTO guardarPaqueteTipoDocumento(PaqueteTipoDocumentoDTO dto) {
        PaqueteTipoDocumento model = PaqueteTipoDocumentoMapper.toModel(dto);
        PaqueteTipoDocumento saved = paqueteTipoDocumentoRepository.save(model);
        return PaqueteTipoDocumentoMapper.toDTO(saved);
    }

    public boolean eliminarPaqueteTipoDocumento(Integer paqueteId, Integer tipoDocumentoId) {
        if (paqueteTipoDocumentoRepository.existsByPaqueteIdAndTipoDocumentoId(paqueteId, tipoDocumentoId)) {
            paqueteTipoDocumentoRepository.deleteByPaqueteIdAndTipoDocumentoId(paqueteId, tipoDocumentoId);
            return true;
        }
        return false;
    }
}