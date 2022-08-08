package com.example.aplicacionempleos.models.repository;

import com.example.aplicacionempleos.models.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}