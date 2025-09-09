package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.SoftwareDian;

public interface SoftwareDianRepository extends JpaRepository<SoftwareDian, Integer> {
    List<SoftwareDian> findByClienteId(Integer clienteId);
}