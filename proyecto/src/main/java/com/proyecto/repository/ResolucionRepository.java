package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Resolucion;
import java.util.List;
public interface ResolucionRepository extends JpaRepository<Resolucion, Integer> {
    List<Resolucion> findByClienteIdAndNumeroResolucionAndTipoDocumento_IdTipoDocumento(Integer clienteId, String numeroResolucion, Integer tipoDocumentoId);
}