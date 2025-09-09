package com.proyecto.service;

import com.proyecto.dto.MensajeDTO;
import com.proyecto.mapper.MensajeMapper;
import com.proyecto.model.Mensaje;
import com.proyecto.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public MensajeDTO getMensaje(Integer id) {
        return mensajeRepository.findById(id)
                .map(MensajeMapper::toDTO)
                .orElse(null);
    }

    public List<MensajeDTO> getMensajes() {
        return mensajeRepository.findAll()
                .stream()
                .map(MensajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MensajeDTO guardarMensaje(MensajeDTO dto) {
        Mensaje model = MensajeMapper.toModel(dto, null);
        Mensaje saved = mensajeRepository.save(model);
        return MensajeMapper.toDTO(saved);
    }

    public boolean eliminarMensaje(Integer id) {
        if (mensajeRepository.existsById(id)) {
            mensajeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}