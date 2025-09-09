package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Resolucion;
import java.util.List;
public interface ResolucionRepository extends JpaRepository<Resolucion, Integer> {
    List<Resolucion> findByClienteIdAndNumeroResolucion(Integer clienteId, String numeroResolucion);
    List<Resolucion> findByClienteIdAndNumeroResolucionAndTipoDocumentoId(Integer clienteId, String numeroResolucion, Integer tipoDocumentoId);
    boolean existsByClienteIdAndNumeroResolucion(Integer clienteId, String numeroResolucion);
    List<Resolucion> findByClienteId(Integer clienteId);
}