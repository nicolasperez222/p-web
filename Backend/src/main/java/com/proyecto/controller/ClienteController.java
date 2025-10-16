package com.proyecto.controller;

import com.proyecto.dto.ClienteDTO;
import com.proyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getClientes() {
        return clienteService.getClientes();
    }

    @PostMapping
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteDTO dto) {
        String resultado = clienteService.insertarOActualizarCliente(dto);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        if (clienteService.eliminarCliente(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}