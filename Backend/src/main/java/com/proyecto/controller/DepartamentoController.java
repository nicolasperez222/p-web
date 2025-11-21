package com.proyecto.controller;

import com.proyecto.dto.DepartamentoDTO;
import com.proyecto.model.Role;
import com.proyecto.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/{id}")
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<DepartamentoDTO> getDepartamento(@PathVariable Integer id) {
        DepartamentoDTO departamento = departamentoService.getDepartamento(id);
        return departamento != null ? ResponseEntity.ok(departamento) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<List<DepartamentoDTO>> getDepartamentos() {
        return ResponseEntity.ok(departamentoService.getDepartamentos());
    }

    @PostMapping
    @Secured({ Role.Code.ADMIN})
    public ResponseEntity<DepartamentoDTO> guardarDepartamento(@RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(departamentoService.guardarDepartamento(dto));
    }

    @DeleteMapping("/{id}")
    @Secured({ Role.Code.ADMIN})
    public ResponseEntity<Void> eliminarDepartamento(@PathVariable Integer id) {
        if (departamentoService.eliminarDepartamento(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}