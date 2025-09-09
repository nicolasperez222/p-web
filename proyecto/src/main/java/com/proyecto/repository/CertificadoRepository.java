package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Certificado;

public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {
    
}