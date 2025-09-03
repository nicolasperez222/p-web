package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificadoRepository extends JpaRepository<Certificado, Integer> {
}