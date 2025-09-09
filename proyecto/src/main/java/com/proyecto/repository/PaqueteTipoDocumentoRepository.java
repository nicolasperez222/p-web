package com.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.model.PaqueteTipoDocumento;
import com.proyecto.model.PaqueteTipoDocumentoId;

public interface PaqueteTipoDocumentoRepository extends JpaRepository<PaqueteTipoDocumento, PaqueteTipoDocumentoId> {

    Optional<PaqueteTipoDocumento> findById_PaqueteIdAndId_TipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);

    boolean existsById_PaqueteIdAndId_TipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);

    void deleteById_PaqueteIdAndId_TipoDocumentoId(Integer paqueteId, Integer tipoDocumentoId);
}