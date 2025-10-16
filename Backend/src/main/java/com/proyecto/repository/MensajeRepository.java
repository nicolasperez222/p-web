package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}