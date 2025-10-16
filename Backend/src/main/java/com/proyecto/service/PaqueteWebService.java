package com.proyecto.service;

import com.proyecto.dto.PaqueteWebDTO;
import com.proyecto.mapper.PaqueteWebMapper;
import com.proyecto.model.PaqueteWeb;
import com.proyecto.repository.PaqueteWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteWebService {

    @Autowired
    private PaqueteWebRepository paqueteWebRepository;

    public PaqueteWebDTO getPaqueteWeb(Integer id) {
        return paqueteWebRepository.findById(id)
                .map(PaqueteWebMapper::toDTO)
                .orElse(null);
    }

    public List<PaqueteWebDTO> getPaquetesWeb() {
        return paqueteWebRepository.findAll()
                .stream()
                .map(PaqueteWebMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaqueteWebDTO guardarPaqueteWeb(PaqueteWebDTO dto) {
        PaqueteWeb model = PaqueteWebMapper.toModel(dto);
        PaqueteWeb saved = paqueteWebRepository.save(model);
        return PaqueteWebMapper.toDTO(saved);
    }

    public boolean eliminarPaqueteWeb(Integer id) {
        if (paqueteWebRepository.existsById(id)) {
            paqueteWebRepository.deleteById(id);
            return true;
        }
        return false;
    }
}