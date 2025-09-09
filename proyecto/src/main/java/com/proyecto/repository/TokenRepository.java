package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}