package com.proyecto.service;

import com.proyecto.dto.ReferenciaPagoDTO;
import com.proyecto.mapper.ReferenciaPagoMapper;
import com.proyecto.model.ReferenciaPago;
import com.proyecto.repository.ReferenciaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenciaPagoService {

    @Autowired
    private ReferenciaPagoRepository referenciaPagoRepository;

    public ReferenciaPagoDTO getReferenciaPago(Integer id) {
        return referenciaPagoRepository.findById(id)
                .map(ReferenciaPagoMapper::toDTO)
                .orElse(null);
    }

    public List<ReferenciaPagoDTO> getReferenciasPago() {
        return referenciaPagoRepository.findAll()
                .stream()
                .map(ReferenciaPagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReferenciaPagoDTO guardarReferenciaPago(ReferenciaPagoDTO dto) {
        ReferenciaPago model = ReferenciaPagoMapper.toModel(dto, null);
        ReferenciaPago saved = referenciaPagoRepository.save(model);
        return ReferenciaPagoMapper.toDTO(saved);
    }

    public boolean eliminarReferenciaPago(Integer id) {
        if (referenciaPagoRepository.existsById(id)) {
            referenciaPagoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}