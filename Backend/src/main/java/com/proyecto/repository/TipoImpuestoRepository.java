package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.TipoImpuesto;

public interface TipoImpuestoRepository extends JpaRepository<TipoImpuesto, Integer> {
}