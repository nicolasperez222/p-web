package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
}