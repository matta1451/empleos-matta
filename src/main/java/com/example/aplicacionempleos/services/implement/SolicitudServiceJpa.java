package com.example.aplicacionempleos.services.implement;

import com.example.aplicacionempleos.models.entity.Solicitud;
import com.example.aplicacionempleos.models.repository.SolicitudRepository;
import com.example.aplicacionempleos.services.interfaces.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SolicitudServiceJpa")
public class SolicitudServiceJpa implements ISolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    @Transactional(readOnly = true)
    public Solicitud findById(Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }
}
