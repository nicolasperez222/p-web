package com.proyecto.controller;

import com.proyecto.dto.DocumentoEmitidoDTO;
import com.proyecto.service.DocumentoEmitidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.proyecto.model.Role;
import java.util.List;

@RestController
@RequestMapping("/api/documentos-emitidos")
public class DocumentoEmitidoController {

    @Autowired
    private DocumentoEmitidoService documentoEmitidoService;

    @GetMapping("/{id}")
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<DocumentoEmitidoDTO> getDocumentoEmitido(@PathVariable Integer id) {
        DocumentoEmitidoDTO documento = documentoEmitidoService.getDocumentoEmitido(id);
        return documento != null ? ResponseEntity.ok(documento) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<List<DocumentoEmitidoDTO>> getDocumentosEmitidos() {
        return ResponseEntity.ok(documentoEmitidoService.getDocumentosEmitidos());
    }

    @PostMapping
    @Secured({ Role.Code.ADMIN})
    public ResponseEntity<DocumentoEmitidoDTO> guardarDocumentoEmitido(@RequestBody DocumentoEmitidoDTO dto) {
        return ResponseEntity.ok(documentoEmitidoService.guardarDocumentoEmitido(dto));
    }

    @DeleteMapping("/{id}")
    @Secured({ Role.Code.ADMIN})
    public ResponseEntity<Void> eliminarDocumentoEmitido(@PathVariable Integer id) {
        if (documentoEmitidoService.eliminarDocumentoEmitido(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{clienteId}")
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<List<DocumentoEmitidoDTO>> getDocumentosPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(documentoEmitidoService.getDocumentosPorCliente(clienteId));
    }

    @GetMapping("/filtrar")
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<List<DocumentoEmitidoDTO>> getDocumentosFiltrados(
            @RequestParam Integer clienteId,
            @RequestParam String prefijo,
            @RequestParam String numero) {
        return ResponseEntity.ok(documentoEmitidoService.getDocumentosFiltrados(clienteId, prefijo, numero));
    }
}