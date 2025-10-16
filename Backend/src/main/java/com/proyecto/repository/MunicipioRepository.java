package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
}