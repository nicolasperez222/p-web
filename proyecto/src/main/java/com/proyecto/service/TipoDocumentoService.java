package com.proyecto.service;

import com.proyecto.dto.TipoDocumentoDTO;
import com.proyecto.mapper.TipoDocumentoMapper;
import com.proyecto.model.TipoDocumento;
import com.proyecto.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoDTO getTipoDocumento(Integer id) {
        return tipoDocumentoRepository.findById(id)
                .map(TipoDocumentoMapper::toDTO)
                .orElse(null);
    }

    public List<TipoDocumentoDTO> getTiposDocumento() {
        return tipoDocumentoRepository.findAll()
                .stream()
                .map(TipoDocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoDocumentoDTO guardarTipoDocumento(TipoDocumentoDTO dto) {
        TipoDocumento model = TipoDocumentoMapper.toModel(dto);
        TipoDocumento saved = tipoDocumentoRepository.save(model);
        return TipoDocumentoMapper.toDTO(saved);
    }

    public boolean eliminarTipoDocumento(Integer id) {
        if (tipoDocumentoRepository.existsById(id)) {
            tipoDocumentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}