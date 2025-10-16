package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.proyecto.model.DocumentoEmitido;

public interface DocumentoEmitidoRepository extends JpaRepository<DocumentoEmitido, Integer> {
    List<DocumentoEmitido> findByClienteIdOrderByFechaDesc(Integer clienteId);
    List<DocumentoEmitido> findByClienteIdAndNumeroAndResolucionPrefijoOrderByFechaDesc(Integer clienteId, String numero, String prefijo);
}