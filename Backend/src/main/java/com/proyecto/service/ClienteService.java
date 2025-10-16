package com.proyecto.service;

import com.proyecto.dto.ClienteDTO;
import com.proyecto.mapper.ClienteMapper;
import com.proyecto.model.Cliente;
import com.proyecto.model.Departamento;
import com.proyecto.model.Municipio;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.DepartamentoRepository;
import com.proyecto.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    public ClienteDTO getCliente(Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        return clienteOpt.map(ClienteMapper::toDTO).orElse(null);
    }

   
    public List<ClienteDTO> getClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

   
    public ClienteDTO obtenerCliente(String nit, String dv) {
        Cliente cliente = clienteRepository.findByNitAndDv(nit, dv);
        return cliente != null ? ClienteMapper.toDTO(cliente) : null;
    }

    public boolean actualizarLogo(Integer idCliente, String logo) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setLogo(logo);
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

  
    public boolean eliminarCliente(Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
   


    
    public String insertarOActualizarClienteA(ClienteDTO clienteDTO) {
        Cliente existente = clienteRepository.findByNitAndDv(clienteDTO.getNit(), clienteDTO.getDv());
        if (existente != null) {
    
            existente.setRepresentanteLegal(clienteDTO.getRepresentanteLegal());
            existente.setRazonSocial(clienteDTO.getRazonSocial());
            existente.setTipoEmpresa(clienteDTO.getTipoEmpresa());
            existente.setResponsabilidadTributaria(clienteDTO.getResponsabilidadTributaria());
            existente.setRegimenIva(clienteDTO.getRegimenIva());
            existente.setDireccion(clienteDTO.getDireccion());
            existente.setEmail(clienteDTO.getEmail());
            existente.setTelefono(clienteDTO.getTelefono());
            existente.setCodigoCiiu(clienteDTO.getCodigoCiiu());
            existente.setImpuesto(clienteDTO.getImpuesto());
            existente.setEstado(clienteDTO.getEstado());
            Departamento dep = departamentoRepository.findById(clienteDTO.getDepartamentoId())
            .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

            Municipio mun = municipioRepository.findById(clienteDTO.getMunicipioId())
                    .orElseThrow(() -> new IllegalArgumentException("Municipio no encontrado"));

            existente.setDepartamento(dep);
            existente.setMunicipio(mun);

            existente.setLogo(clienteDTO.getLogo());
            clienteRepository.save(existente);
            return "Cliente actualizado exitosamente";
        } else {
            Cliente nuevo = ClienteMapper.toModel(clienteDTO, null, null);
            clienteRepository.save(nuevo);
            return "Cliente insertado exitosamente";
        }
    }

    public String insertarOActualizarCliente(ClienteDTO clienteDTO) {
        Cliente existente = clienteRepository.findByNitAndDv(clienteDTO.getNit(), clienteDTO.getDv());
        if (existente != null) {
            // Actualizar cliente existente
            existente.setRepresentanteLegal(clienteDTO.getRepresentanteLegal());
            existente.setRazonSocial(clienteDTO.getRazonSocial());
            existente.setTipoEmpresa(clienteDTO.getTipoEmpresa());
            existente.setResponsabilidadTributaria(clienteDTO.getResponsabilidadTributaria());
            existente.setRegimenIva(clienteDTO.getRegimenIva());
            existente.setDireccion(clienteDTO.getDireccion());
            existente.setEmail(clienteDTO.getEmail());
            existente.setTelefono(clienteDTO.getTelefono());
            existente.setCodigoCiiu(clienteDTO.getCodigoCiiu());
            existente.setImpuesto(clienteDTO.getImpuesto());
            existente.setEstado(clienteDTO.getEstado());
    
            // Buscar y asignar departamento y municipio
            Departamento dep = departamentoRepository.findById(clienteDTO.getDepartamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));
            Municipio mun = municipioRepository.findById(clienteDTO.getMunicipioId())
                    .orElseThrow(() -> new IllegalArgumentException("Municipio no encontrado"));
    
            existente.setDepartamento(dep);
            existente.setMunicipio(mun);
    
            // Actualizar logo si está presente
            existente.setLogo(clienteDTO.getLogo());
    
            clienteRepository.save(existente);
            return "Cliente actualizado exitosamente";
        } else {
            // Insertar nuevo cliente
            Cliente nuevo = ClienteMapper.toModel(clienteDTO, null, null);
            clienteRepository.save(nuevo);
            return "Cliente insertado exitosamente";
        }
    }
}