package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.PaqueteTipoDocumento;

public interface PaqueteTipoDocumentoRepository extends JpaRepository<PaqueteTipoDocumento, Integer> {
}