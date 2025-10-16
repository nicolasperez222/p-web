package com.proyecto.controller;

import com.proyecto.dto.TipoDocumentoDTO;
import com.proyecto.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-documento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

   
    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> getTiposDocumento() {
        return ResponseEntity.ok(tipoDocumentoService.getTiposDocumento());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoDTO> getTipoDocumento(@PathVariable Integer id) {
        TipoDocumentoDTO dto = tipoDocumentoService.getTipoDocumento(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<TipoDocumentoDTO> guardarTipoDocumento(@RequestBody TipoDocumentoDTO dto) {
        return ResponseEntity.ok(tipoDocumentoService.guardarTipoDocumento(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDocumento(@PathVariable Integer id) {
        boolean eliminado = tipoDocumentoService.eliminarTipoDocumento(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
