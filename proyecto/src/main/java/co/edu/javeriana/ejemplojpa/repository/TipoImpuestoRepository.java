package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.TipoImpuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoImpuestoRepository extends JpaRepository<TipoImpuesto, Integer> {
}