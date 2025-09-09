package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Paquete;

import java.util.Date;
import java.util.Optional;

public interface PaqueteRepository extends JpaRepository<Paquete, Integer> {
    Optional<Paquete> findFirstByClienteIdAndEstadoAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
        Integer clienteId, Integer estado, Date fechaFin
    );
    Optional<Paquete> findFirstByClienteIdAndEstadoAndDocumentosRestanteGreaterThanAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
        Integer clienteId, Integer estado, Integer documentosRestante, Date fechaFin
    );
}