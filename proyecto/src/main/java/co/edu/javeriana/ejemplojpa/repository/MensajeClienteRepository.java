package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.MensajeCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeClienteRepository extends JpaRepository<MensajeCliente, Integer> {
}