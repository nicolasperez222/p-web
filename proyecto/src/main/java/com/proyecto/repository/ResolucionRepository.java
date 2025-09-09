package com.proyecto.repository;

import com.proyecto.model.Resolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResolucionRepository extends JpaRepository<Resolucion, Integer> {

    List<Resolucion> findByClienteIdAndNumeroResolucion(Integer clienteId, String numeroResolucion);

    List<Resolucion> findByClienteIdAndNumeroResolucionAndTipoDocumento_IdTipoDocumento(
        Integer clienteId, String numeroResolucion, Integer tipoDocumentoId
    );

    boolean existsByClienteIdAndNumeroResolucion(Integer clienteId, String numeroResolucion);

    List<Resolucion> findByClienteId(Integer clienteId);
}
