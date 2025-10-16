package com.proyecto.controller;

import com.proyecto.dto.CertificadoDTO;
import com.proyecto.service.CertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> getCertificado(@PathVariable Integer id) {
        CertificadoDTO certificado = certificadoService.getCertificado(id);
        return certificado != null ? ResponseEntity.ok(certificado) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> getCertificados() {
        return ResponseEntity.ok(certificadoService.getCertificados());
    }

    @PostMapping
    public ResponseEntity<CertificadoDTO> guardarCertificado(@RequestBody CertificadoDTO dto) {
        return ResponseEntity.ok(certificadoService.guardarCertificado(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCertificado(@PathVariable Integer id) {
        if (certificadoService.eliminarCertificado(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}