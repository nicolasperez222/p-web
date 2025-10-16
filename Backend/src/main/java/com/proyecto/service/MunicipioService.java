package com.proyecto.service;

import com.proyecto.dto.MunicipioDTO;
import com.proyecto.mapper.MunicipioMapper;
import com.proyecto.model.Municipio;
import com.proyecto.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public MunicipioDTO getMunicipio(Integer id) {
        return municipioRepository.findById(id)
                .map(MunicipioMapper::toDTO)
                .orElse(null);
    }

    public List<MunicipioDTO> getMunicipios() {
        return municipioRepository.findAll()
                .stream()
                .map(MunicipioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MunicipioDTO guardarMunicipio(MunicipioDTO dto) {
        Municipio model = MunicipioMapper.toModel(dto);
        Municipio saved = municipioRepository.save(model);
        return MunicipioMapper.toDTO(saved);
    }

    public boolean eliminarMunicipio(Integer id) {
        if (municipioRepository.existsById(id)) {
            municipioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}