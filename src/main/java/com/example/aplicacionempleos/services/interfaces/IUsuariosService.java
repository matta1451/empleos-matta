package com.example.aplicacionempleos.services.interfaces;

import com.example.aplicacionempleos.models.entity.Usuario;

import java.util.List;

public interface IUsuariosService {
    void save(Usuario usuario);

    void deleteById(Long id);

    List<Usuario> findAll();

    Usuario findByUsername(String username);

    Usuario findById(Long id);
}
