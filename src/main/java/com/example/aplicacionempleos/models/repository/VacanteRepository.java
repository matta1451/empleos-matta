package com.example.aplicacionempleos.models.repository;

import com.example.aplicacionempleos.models.entity.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacanteRepository extends JpaRepository<Vacante, Long> {
    List<Vacante> findByEstatus(String estatus);
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);
}