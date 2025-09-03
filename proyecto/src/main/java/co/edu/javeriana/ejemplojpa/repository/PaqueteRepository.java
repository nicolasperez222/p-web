package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaqueteRepository extends JpaRepository<Paquete, Integer> {
}