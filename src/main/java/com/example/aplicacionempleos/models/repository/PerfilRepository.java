package com.example.aplicacionempleos.models.repository;

import com.example.aplicacionempleos.models.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findFirstByPerfil(String perfil);
}