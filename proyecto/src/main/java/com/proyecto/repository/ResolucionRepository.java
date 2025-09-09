package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Resolucion;

public interface ResolucionRepository extends JpaRepository<Resolucion, Integer> {
}