package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.DocumentoEmitido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoEmitidoRepository extends JpaRepository<DocumentoEmitido, Integer> {
}