package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.proyecto.service.ResolucionService;
import com.proyecto.dto.ResolucionDTO;

@RestController
@RequestMapping("/api/resoluciones")
public class ResolucionController {

    @Autowired
    private ResolucionService resolucionService;

    @GetMapping
    public ResponseEntity<List<ResolucionDTO>> getResoluciones() {
        return ResponseEntity.ok(resolucionService.getResoluciones());
    }

    @PostMapping
    public ResponseEntity<ResolucionDTO> guardarResolucion(@RequestBody ResolucionDTO dto) {
        return ResponseEntity.ok(resolucionService.guardarResolucion(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResolucion(@PathVariable Integer id) {
        if (resolucionService.eliminarResolucion(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
