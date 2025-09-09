package com.proyecto.service;

import com.proyecto.dto.CertificadoDTO;
import com.proyecto.mapper.CertificadoMapper;
import com.proyecto.model.Certificado;
import com.proyecto.repository.CertificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    public CertificadoDTO getCertificado(Integer id) {
        return certificadoRepository.findById(id)
                .map(CertificadoMapper::toDTO)
                .orElse(null);
    }

    public List<CertificadoDTO> getCertificados() {
        return certificadoRepository.findAll()
                .stream()
                .map(CertificadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CertificadoDTO guardarCertificado(CertificadoDTO dto) {
        Certificado model = CertificadoMapper.toModel(dto, null);
        Certificado saved = certificadoRepository.save(model);
        return CertificadoMapper.toDTO(saved);
    }

    public boolean eliminarCertificado(Integer id) {
        if (certificadoRepository.existsById(id)) {
            certificadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}