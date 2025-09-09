package com.proyecto.service;

import com.proyecto.dto.DepartamentoDTO;
import com.proyecto.mapper.DepartamentoMapper;
import com.proyecto.model.Departamento;
import com.proyecto.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoDTO getDepartamento(Integer id) {
        return departamentoRepository.findById(id)
                .map(DepartamentoMapper::toDTO)
                .orElse(null);
    }

    public List<DepartamentoDTO> getDepartamentos() {
        return departamentoRepository.findAll()
                .stream()
                .map(DepartamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartamentoDTO guardarDepartamento(DepartamentoDTO dto) {
        Departamento model = DepartamentoMapper.toModel(dto);
        Departamento saved = departamentoRepository.save(model);
        return DepartamentoMapper.toDTO(saved);
    }

    public boolean eliminarDepartamento(Integer id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}