package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}