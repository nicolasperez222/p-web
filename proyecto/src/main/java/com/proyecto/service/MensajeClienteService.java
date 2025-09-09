package com.proyecto.service;

import com.proyecto.dto.MensajeClienteDTO;
import com.proyecto.mapper.MensajeClienteMapper;
import com.proyecto.model.MensajeCliente;
import com.proyecto.repository.MensajeClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeClienteService {

    @Autowired
    private MensajeClienteRepository mensajeClienteRepository;

    public MensajeClienteDTO getMensajeCliente(Integer id) {
        return mensajeClienteRepository.findById(id)
                .map(MensajeClienteMapper::toDTO)
                .orElse(null);
    }

    public List<MensajeClienteDTO> getMensajesCliente() {
        return mensajeClienteRepository.findAll()
                .stream()
                .map(MensajeClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MensajeClienteDTO guardarMensajeCliente(MensajeClienteDTO dto) {
        MensajeCliente model = MensajeClienteMapper.toModel(dto, null, null);
        MensajeCliente saved = mensajeClienteRepository.save(model);
        return MensajeClienteMapper.toDTO(saved);
    }

    public boolean eliminarMensajeCliente(Integer id) {
        if (mensajeClienteRepository.existsById(id)) {
            mensajeClienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}