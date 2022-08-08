package com.example.aplicacionempleos.models.repository;

import com.example.aplicacionempleos.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}