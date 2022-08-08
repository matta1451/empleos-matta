package com.example.aplicacionempleos.services.interfaces;

import com.example.aplicacionempleos.models.entity.Solicitud;

import java.util.List;

public interface ISolicitudService {
    Solicitud findById(Long id);

    void save(Solicitud solicitud);

    List<Solicitud> findAll();

    void deleteById(Long id);
}
