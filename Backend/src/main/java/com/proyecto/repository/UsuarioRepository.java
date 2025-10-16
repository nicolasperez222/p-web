package com.proyecto.repository;

import com.proyecto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByCliente(Integer cliente);
    Usuario findByUsuario(String usuario);
}