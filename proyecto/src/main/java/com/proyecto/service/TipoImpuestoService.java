package com.proyecto.service;

import com.proyecto.dto.TipoImpuestoDTO;
import com.proyecto.mapper.TipoImpuestoMapper;
import com.proyecto.model.TipoImpuesto;
import com.proyecto.repository.TipoImpuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoImpuestoService {

    @Autowired
    private TipoImpuestoRepository tipoImpuestoRepository;

    public TipoImpuestoDTO getTipoImpuesto(Integer id) {
        return tipoImpuestoRepository.findById(id)
                .map(TipoImpuestoMapper::toDTO)
                .orElse(null);
    }

    public List<TipoImpuestoDTO> getTiposImpuesto() {
        return tipoImpuestoRepository.findAll()
                .stream()
                .map(TipoImpuestoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoImpuestoDTO guardarTipoImpuesto(TipoImpuestoDTO dto) {
        TipoImpuesto model = TipoImpuestoMapper.toModel(dto);
        TipoImpuesto saved = tipoImpuestoRepository.save(model);
        return TipoImpuestoMapper.toDTO(saved);
    }

    public boolean eliminarTipoImpuesto(Integer id) {
        if (tipoImpuestoRepository.existsById(id)) {
            tipoImpuestoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}