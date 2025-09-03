package co.edu.javeriana.ejemplojpa.repository;

import co.edu.javeriana.ejemplojpa.model.PaqueteTipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaqueteTipoDocumentoRepository extends JpaRepository<PaqueteTipoDocumento, Integer> {
}