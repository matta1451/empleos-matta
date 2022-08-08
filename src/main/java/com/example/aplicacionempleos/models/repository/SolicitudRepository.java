package com.example.aplicacionempleos.models.repository;

import com.example.aplicacionempleos.models.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}