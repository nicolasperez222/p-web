package com.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.PaqueteTipoDocumento;

public interface PaqueteTipoDocumentoRepository extends JpaRepository<PaqueteTipoDocumento, Integer> {
    
    Optional<PaqueteTipoDocumento> findByPaqueteIdAndTipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);

    boolean existsByPaqueteIdAndTipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);

    void deleteByPaqueteIdAndTipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);
}