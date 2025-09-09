package com.proyecto.service;

import com.proyecto.dto.SoftwareDianDTO;
import com.proyecto.mapper.SoftwareDianMapper;
import com.proyecto.model.Cliente;
import com.proyecto.model.SoftwareDian;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.SoftwareDianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SoftwareDianService {

    @Autowired
    private SoftwareDianRepository softwareDianRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public List<SoftwareDianDTO> getSoftware(Integer clienteId) {
        List<SoftwareDian> lista = softwareDianRepository.findByClienteId(clienteId);
        List<SoftwareDianDTO> dtos = new ArrayList<>();
        for (SoftwareDian sw : lista) {
            dtos.add(SoftwareDianMapper.toDTO(sw));
        }
        return dtos;
    }

 
    @Transactional
    public boolean actualizarAmbienteFE(Integer clienteId, Integer ambienteFE) {
        List<SoftwareDian> lista = softwareDianRepository.findByClienteId(clienteId);
        if (lista.isEmpty()) return false;
        for (SoftwareDian sw : lista) {
            sw.setAmbienteFe(ambienteFE);
            softwareDianRepository.save(sw);
        }
        return true;
    }

 
    @Transactional
    public SoftwareDianDTO guardarSoftwareDian(SoftwareDianDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        SoftwareDian model = SoftwareDianMapper.toModel(dto, cliente);
        SoftwareDian saved = softwareDianRepository.save(model);
        return SoftwareDianMapper.toDTO(saved);
    }

  
    public List<Map<String, Object>> getSoftwareReq(Integer clienteId) {
        List<SoftwareDian> lista = softwareDianRepository.findByClienteId(clienteId);
        if (lista.isEmpty()) return null;

        List<Map<String, Object>> result = new ArrayList<>();
        for (SoftwareDian sw : lista) {
            String urlFE = sw.getAmbienteFe() != null && sw.getAmbienteFe() == 2
                    ? "https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc"
                    : sw.getAmbienteFe() != null && sw.getAmbienteFe() == 1
                        ? "https://vpfe.dian.gov.co/WcfDianCustomerServices.svc"
                        : null;

            String urlEpos = sw.getAmbienteEpos() != null && sw.getAmbienteEpos() == 2
                    ? "https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc"
                    : sw.getAmbienteEpos() != null && sw.getAmbienteEpos() == 1
                        ? "https://vpfe.dian.gov.co/WcfDianCustomerServices.svc"
                        : null;

            Map<String, Object> map = new HashMap<>();
            map.put("id", sw.getIdSoftware());
            map.put("identifier", sw.getIdSoftwareFacturacion());
            map.put("pin", sw.getPinFacturacion());
            map.put("identifier_eqdocs", sw.getIdSoftwareEpos());
            map.put("pin_eqdocs", sw.getPinEpos());
            map.put("url", urlFE);
            map.put("url_eqdocs", urlEpos);
            result.add(map);
        }
        return result;
    }

   
    @Transactional
    public Map<String, Object> saveOrUpdateSoftware(Integer clienteId, SoftwareDianDTO dto) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        List<SoftwareDian> existentes = softwareDianRepository.findByClienteId(clienteId);

        if (!existentes.isEmpty()) {
            // Actualizar el primero (o todos si hay más de uno)
            for (SoftwareDian sw : existentes) {
                sw.setIdSoftwareFacturacion(dto.getIdSoftwareFacturacion());
                sw.setPinFacturacion(dto.getPinFacturacion());
                sw.setIdSoftwareEpos(dto.getIdSoftwareEpos());
                sw.setPinEpos(dto.getPinEpos());
                sw.setAmbienteFe(dto.getAmbienteFe());
                sw.setAmbienteEpos(dto.getAmbienteEpos());
                softwareDianRepository.save(sw);
            }
            response.put("success", true);
            response.put("message", "Software actualizado exitosamente.");
        } else {
            SoftwareDian nuevo = SoftwareDianMapper.toModel(dto, cliente);
            softwareDianRepository.save(nuevo);
            response.put("success", true);
            response.put("message", "Software guardado exitosamente.");
        }
        return response;
    }

 
    @Transactional
    public boolean eliminarSoftware(Integer idSoftware) {
        if (softwareDianRepository.existsById(idSoftware)) {
            softwareDianRepository.deleteById(idSoftware);
            return true;
        }
        return false;
    }
}