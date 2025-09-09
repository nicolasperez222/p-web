package com.proyecto.service;

import com.proyecto.dto.UsuarioDTO;
import com.proyecto.mapper.UsuarioMapper;
import com.proyecto.model.Usuario;
import com.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO getUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO)
                .orElse(null);
    }

    public List<UsuarioDTO> getUsuariosPorCliente(Integer clienteId) {
        return usuarioRepository.findByCliente(clienteId)
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioPorNombre(String usuario) {
        Usuario user = usuarioRepository.findByUsuario(usuario);
        return user != null ? UsuarioMapper.toDTO(user) : null;
    }

    public UsuarioDTO guardarUsuario(UsuarioDTO dto) {
        Usuario model = UsuarioMapper.toModel(dto);
        Usuario saved = usuarioRepository.save(model);
        return UsuarioMapper.toDTO(saved);
    }

    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}