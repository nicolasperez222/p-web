package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.MensajeCliente;

public interface MensajeClienteRepository extends JpaRepository<MensajeCliente, Integer> {
}