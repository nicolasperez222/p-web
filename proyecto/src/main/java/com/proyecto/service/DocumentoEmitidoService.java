package com.proyecto.service;

import com.proyecto.dto.DocumentoEmitidoDTO;
import com.proyecto.mapper.DocumentoEmitidoMapper;
import com.proyecto.model.DocumentoEmitido;
import com.proyecto.model.Paquete;
import com.proyecto.repository.DocumentoEmitidoRepository;
import com.proyecto.repository.PaqueteRepository;
import com.proyecto.repository.ResolucionRepository;
import com.proyecto.repository.TipoDocumentoRepository;
import com.proyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoEmitidoService {

    @Autowired
    private DocumentoEmitidoRepository documentoEmitidoRepository;

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<DocumentoEmitidoDTO> getDocumentosPorCliente(Integer idCliente) {
        List<DocumentoEmitido> docs = documentoEmitidoRepository.findByClienteIdOrderByFechaDesc(idCliente);
        return docs.stream().map(DocumentoEmitidoMapper::toDTO).collect(Collectors.toList());
    }

  
    public List<DocumentoEmitidoDTO> getDocumentosFiltrados(Integer idCliente, String prefijo, String numero) {
        List<DocumentoEmitido> docs = documentoEmitidoRepository.findByClienteIdAndNumeroAndResolucionPrefijoOrderByFechaDesc(
                idCliente, numero, prefijo
        );
        return docs.stream().map(DocumentoEmitidoMapper::toDTO).collect(Collectors.toList());
    }

   
    public DocumentoEmitidoDTO guardarDocumento(DocumentoEmitidoDTO dto) {
        DocumentoEmitido model = DocumentoEmitidoMapper.toModel(
                dto,
                tipoDocumentoRepository.findById(dto.getTipoDocumentoId()).orElse(null),
                resolucionRepository.findById(dto.getResolucionId()).orElse(null),
                clienteRepository.findById(dto.getClienteId()).orElse(null)
        );
        DocumentoEmitido saved = documentoEmitidoRepository.save(model);
        return DocumentoEmitidoMapper.toDTO(saved);
    }

    @Transactional
    public String consumirDocumento(Integer clienteId) {
        Optional<Paquete> paqueteOpt = paqueteRepository.findFirstByClienteIdAndEstadoAndDocumentosRestanteGreaterThanAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
                clienteId, 1, 0, new java.util.Date()
        );
        if (paqueteOpt.isEmpty()) {
            return "No hay paquete activo con documentos disponibles";
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
        return "Documento consumido correctamente. Documentos restantes: " + paquete.getDocumentosRestante();
    }
}