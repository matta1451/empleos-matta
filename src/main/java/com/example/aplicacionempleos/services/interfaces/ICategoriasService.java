package com.example.aplicacionempleos.services.interfaces;

import com.example.aplicacionempleos.models.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriasService {
    void save(Categoria categoria);

    List<Categoria> findAll();

    Categoria findById(Long id);

    void deleteById(Long id);

    Page<Categoria> findAllPage(Pageable pageable);
}
