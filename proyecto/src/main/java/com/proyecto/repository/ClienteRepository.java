package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByNitAndDv(String nit, String dv);
}