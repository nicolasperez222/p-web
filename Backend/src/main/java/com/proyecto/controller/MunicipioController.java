package com.proyecto.controller;

import com.proyecto.dto.MunicipioDTO;
import com.proyecto.model.Role;
import com.proyecto.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/{id}")
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<MunicipioDTO> getMunicipio(@PathVariable Integer id) {
        MunicipioDTO dto = municipioService.getMunicipio(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Secured({ Role.Code.USER, Role.Code.ADMIN})
    public ResponseEntity<List<MunicipioDTO>> getMunicipios() {
        return ResponseEntity.ok(municipioService.getMunicipios());
    }

    @PostMapping
    @Secured({ Role.Code.ADMIN })
    public ResponseEntity<MunicipioDTO> guardarMunicipio(@RequestBody MunicipioDTO dto) {
        MunicipioDTO saved = municipioService.guardarMunicipio(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    @Secured({ Role.Code.ADMIN })
    public ResponseEntity<Void> eliminarMunicipio(@PathVariable Integer id) {
        boolean eliminado = municipioService.eliminarMunicipio(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
