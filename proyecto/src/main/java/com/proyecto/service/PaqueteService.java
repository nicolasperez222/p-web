package com.proyecto.service;

import com.proyecto.dto.PaqueteDTO;
import com.proyecto.mapper.PaqueteMapper;
import com.proyecto.model.Paquete;
import com.proyecto.model.PaqueteTipoDocumento;

import com.proyecto.repository.PaqueteRepository;
import com.proyecto.repository.PaqueteTipoDocumentoRepository;
import com.proyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private PaqueteTipoDocumentoRepository paqueteTipoDocumentoRepository;


    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Map<String, Object> guardarPaquete(PaqueteDTO paqueteDTO, List<Integer> tiposDocumentoIds) {
        Map<String, Object> response = new HashMap<>();
        try {

            Optional<Paquete> paqueteActivoOpt = paqueteRepository
                    .findFirstByClienteIdAndEstadoAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
                            paqueteDTO.getClienteId(), 1, new Date()
                    );

            int documentosTotales = paqueteDTO.getDocumentosRestante() != null ? paqueteDTO.getDocumentosRestante() : 0;

            if (paqueteActivoOpt.isPresent()) {
                Paquete paqueteActivo = paqueteActivoOpt.get();
                int documentosActivos = paqueteActivo.getDocumentosRestante() != null ? paqueteActivo.getDocumentosRestante() : 0;
                documentosTotales += documentosActivos;
                // Desactivar paquete activo
                paqueteActivo.setEstado(0);
                paqueteRepository.save(paqueteActivo);
            }

            Paquete paquete = PaqueteMapper.toModel(paqueteDTO, clienteRepository.findById(paqueteDTO.getClienteId()).orElse(null));
            paquete.setDocumentosRestante(documentosTotales);
            paquete.setPeriodo(paquete.getPeriodo() != null ? paquete.getPeriodo().toUpperCase() : null);
            paquete = paqueteRepository.save(paquete);

            for (Integer tipoDocumentoId : tiposDocumentoIds) {
                PaqueteTipoDocumento ptd = new PaqueteTipoDocumento();
                ptd.setPaqueteId(paquete.getIdPaquete());
                ptd.setTipoDocumentoId(tipoDocumentoId);
                paqueteTipoDocumentoRepository.save(ptd);
            }

            response.put("success", true);
            response.put("message", "Paquete guardado exitosamente");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error guardando el paquete");
            response.put("error", e.getMessage());
        }
        return response;
    }


    @Transactional
    public Map<String, Object> consumirDocumento(Integer clienteId) {
        Map<String, Object> response = new HashMap<>();
        Optional<Paquete> paqueteOpt = paqueteRepository
                .findFirstByClienteIdAndEstadoAndDocumentosRestanteGreaterThanAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
                        clienteId, 1, 0, new Date()
                );
        if (paqueteOpt.isEmpty()) {
            response.put("success", false);
            response.put("message", "No hay paquete activo con documentos disponibles");
            return response;
        }
        Paquete paquete = paqueteOpt.get();
        int nuevoValor = paquete.getDocumentosRestante() - 1;
        if (nuevoValor <= 0) {
            paquete.setDocumentosRestante(0);
            paquete.setEstado(0);
        } else {
            paquete.setDocumentosRestante(nuevoValor);
        }
        paqueteRepository.save(paquete);
        response.put("success", true);
        response.put("message", "Documento consumido correctamente");
        response.put("documentos_restante", paquete.getDocumentosRestante());
        return response;
    }

    public Map<String, Object> verificarPaqueteActivo(Integer clienteId) {
        Map<String, Object> response = new HashMap<>();
        Optional<Paquete> paqueteOpt = paqueteRepository
                .findFirstByClienteIdAndEstadoAndDocumentosRestanteGreaterThanAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
                        clienteId, 1, 0, new Date()
                );
        if (paqueteOpt.isPresent()) {
            response.put("success", true);
            response.put("message", "Cliente tiene un paquete activo");
            response.put("paquete", PaqueteMapper.toDTO(paqueteOpt.get()));
        } else {
            response.put("success", false);
            response.put("message", "Cliente no tiene un paquete activo válido");
        }
        return response;
    }
}