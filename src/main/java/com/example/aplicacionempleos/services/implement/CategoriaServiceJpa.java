package com.example.aplicacionempleos.services.implement;

import com.example.aplicacionempleos.models.entity.Categoria;
import com.example.aplicacionempleos.models.repository.CategoriaRepository;
import com.example.aplicacionempleos.services.interfaces.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CategoriaServiceJpa")
public class CategoriaServiceJpa implements ICategoriasService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Categoria> findAllPage(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }
}
