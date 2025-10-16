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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;

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


    public DocumentoEmitidoDTO guardarDocumentoEmitido(DocumentoEmitidoDTO dto) {

        var tipo = tipoDocumentoRepository.findById(dto.getTipoDocumentoId()).orElse(null);
        var resolucion = resolucionRepository.findById(dto.getResolucionId()).orElse(null);
        var cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);

        if (cliente == null || tipo == null || resolucion == null) {
            throw new IllegalArgumentException("Cliente, tipo de documento o resolución inválidos.");
        }

     
        DocumentoEmitido documento = DocumentoEmitidoMapper.toModel(dto, tipo, resolucion, cliente);

     

        BigDecimal valorBruto = Optional.ofNullable(documento.getValorBruto()).orElse(BigDecimal.ZERO);
        BigDecimal descuentos = Optional.ofNullable(documento.getDescuentos()).orElse(BigDecimal.ZERO);
        BigDecimal subtotal = Optional.ofNullable(documento.getSubtotal()).orElse(BigDecimal.ZERO);
        BigDecimal impuestos = Optional.ofNullable(documento.getImpuestos()).orElse(BigDecimal.ZERO);
        BigDecimal total = Optional.ofNullable(documento.getTotal()).orElse(BigDecimal.ZERO);

   
        descuentos = descuentos.max(BigDecimal.ZERO);
        impuestos = impuestos.max(BigDecimal.ZERO);

    
        if (valorBruto.compareTo(BigDecimal.ZERO) == 0 && subtotal.compareTo(BigDecimal.ZERO) > 0) {
            valorBruto = subtotal.add(descuentos);
        } else if (subtotal.compareTo(BigDecimal.ZERO) == 0 && valorBruto.compareTo(BigDecimal.ZERO) > 0) {
            subtotal = valorBruto.subtract(descuentos);
        } else if (valorBruto.compareTo(BigDecimal.ZERO) == 0 && subtotal.compareTo(BigDecimal.ZERO) == 0) {
            subtotal = BigDecimal.ZERO;
            valorBruto = descuentos;
        }


        total = subtotal.add(impuestos);

        documento.setValorBruto(valorBruto);
        documento.setSubtotal(subtotal);
        documento.setTotal(total);

        if (documento.getCufeCude() == null || documento.getCufeCude().isBlank()) {
            documento.setCufeCude(generarCufe(documento));
        }

        // Guardar en BD
        DocumentoEmitido saved = documentoEmitidoRepository.save(documento);
        return DocumentoEmitidoMapper.toDTO(saved);
    }

    @Transactional
    public String consumirDocumento(Integer clienteId) {
        Optional<Paquete> paqueteOpt = paqueteRepository
                .findFirstByClienteIdAndEstadoAndDocumentosRestanteGreaterThanAndFechaFinAfterOrFechaFinIsNullOrderByIdPaqueteAsc(
                        clienteId, 1, 0, new Date()
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
    
    public boolean eliminarDocumentoEmitido(Integer id) {
        if (documentoEmitidoRepository.existsById(id)) {
            documentoEmitidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public DocumentoEmitidoDTO getDocumentoEmitido(Integer id) {
        return documentoEmitidoRepository.findById(id)
                .map(DocumentoEmitidoMapper::toDTO)
                .orElse(null);
    }

    public List<DocumentoEmitidoDTO> getDocumentosEmitidos() {
        return documentoEmitidoRepository.findAll()
                .stream()
                .map(DocumentoEmitidoMapper::toDTO)
                .collect(Collectors.toList());
    }



    private String generarCufe(DocumentoEmitido doc) {
        try {
            StringBuilder base = new StringBuilder();
            base.append(doc.getNumero() != null ? doc.getNumero() : "");
            base.append("|");
            base.append(doc.getFecha() != null ? doc.getFecha().toString() : "");
            base.append("|");
            base.append(doc.getTotal() != null ? doc.getTotal().toString() : "0");
            base.append("|");
            base.append(doc.getCliente() != null && doc.getCliente().getId() != null ? doc.getCliente().getId().toString() : "");
            base.append("|");
           
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(base.toString().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando CUFE/CUDE", e);
        }
    }
}
