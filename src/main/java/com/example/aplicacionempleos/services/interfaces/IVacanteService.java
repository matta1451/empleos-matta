package com.example.aplicacionempleos.services.interfaces;

import com.example.aplicacionempleos.models.entity.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVacanteService {
    List<Vacante> findAll();

    Vacante findById(Long id);

    void save(Vacante vacante);

    List<Vacante> findByDestacadas();

    void deleteById(Long id);

    List<Vacante> findByExample(Example<Vacante> example);

    Page<Vacante> findAllPage(Pageable pageable);
}
