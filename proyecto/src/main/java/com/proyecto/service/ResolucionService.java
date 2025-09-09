package com.proyecto.service;

import com.proyecto.dto.ResolucionDTO;
import com.proyecto.mapper.ResolucionMapper;
import com.proyecto.model.Cliente;
import com.proyecto.model.Resolucion;
import com.proyecto.model.TipoDocumento;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.ResolucionRepository;
import com.proyecto.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ResolucionService {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

 
    public List<ResolucionDTO> getResolucion(Integer clienteId, String numeroResolucion) {
        List<Resolucion> resoluciones = resolucionRepository.findByClienteIdAndNumeroResolucion(clienteId, numeroResolucion);
        List<ResolucionDTO> dtos = new ArrayList<>();
        for (Resolucion r : resoluciones) {
            dtos.add(ResolucionMapper.toDTO(r));
        }
        return dtos;
    }


    public List<ResolucionDTO> getResoluciones(Integer clienteId, String numeroResolucion, Integer tipoDocumentoId) {
        List<Resolucion> resoluciones = resolucionRepository.findByClienteIdAndNumeroResolucionAndTipoDocumento_IdTipoDocumento(
                clienteId, numeroResolucion, tipoDocumentoId
        );
        List<ResolucionDTO> dtos = new ArrayList<>();
        for (Resolucion r : resoluciones) {
            dtos.add(ResolucionMapper.toDTO(r));
        }
        return dtos;
    }

    public List<ResolucionDTO> getResoluciones() {
        List<Resolucion> resoluciones = resolucionRepository.findAll();
        List<ResolucionDTO> dtos = new ArrayList<>();
        for (Resolucion resolucion : resoluciones) {
            dtos.add(ResolucionMapper.toDTO(resolucion));
        }
        return dtos;
    }
    
    @Transactional
    public ResolucionDTO guardarResolucion(ResolucionDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(dto.getTipoDocumentoId()).orElse(null);

        Resolucion resolucion = ResolucionMapper.toModel(dto, cliente, tipoDocumento);
        resolucion = resolucionRepository.save(resolucion);

        return ResolucionMapper.toDTO(resolucion);
    }
    @Transactional
    public void guardarResoluciones(Integer clienteId, Integer tipoDocumentoId, List<ResolucionDTO> resoluciones) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(tipoDocumentoId).orElse(null);

        for (ResolucionDTO res : resoluciones) {
            boolean existe = existeResolucion(clienteId, res.getNumeroResolucion());
            Resolucion resolucion;
            if (!existe) {
                resolucion = ResolucionMapper.toModel(res, cliente, tipoDocumento);
                resolucion.setCliente(cliente);
                resolucion.setTipoDocumento(tipoDocumento);
                resolucionRepository.save(resolucion);
            } else {
               
                List<Resolucion> existentes = resolucionRepository.findByClienteIdAndNumeroResolucion(clienteId, res.getNumeroResolucion());
                if (!existentes.isEmpty()) {
                    resolucion = existentes.get(0);
                    resolucion.setTipoDocumento(tipoDocumento);
                    resolucion.setPrefijo(res.getPrefijo());
                    resolucion.setFechaCreacion(res.getFechaCreacion());
                    resolucion.setLlaveTecnica(res.getLlaveTecnica());
                    resolucion.setDesde(res.getDesde());
                    resolucion.setHasta(res.getHasta());
                    resolucion.setFechaDesde(res.getFechaDesde());
                    resolucion.setFechaHasta(res.getFechaHasta());
                    resolucionRepository.save(resolucion);
                }
            }
        }
    }

 
    public boolean existeResolucion(Integer clienteId, String numeroResolucion) {
        return resolucionRepository.existsByClienteIdAndNumeroResolucion(clienteId, numeroResolucion);
    }

  
    public List<ResolucionDTO> getResolucionesPorCliente(Integer clienteId) {
        List<Resolucion> resoluciones = resolucionRepository.findByClienteId(clienteId);
        List<ResolucionDTO> dtos = new ArrayList<>();
        for (Resolucion r : resoluciones) {
            dtos.add(ResolucionMapper.toDTO(r));
        }
        return dtos;
    }


    public boolean eliminarResolucion(Integer idResolucion) {
        if (resolucionRepository.existsById(idResolucion)) {
            resolucionRepository.deleteById(idResolucion);
            return true;
        }
        return false;
    }
}