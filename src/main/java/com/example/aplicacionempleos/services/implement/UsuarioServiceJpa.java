package com.example.aplicacionempleos.services.implement;

import com.example.aplicacionempleos.models.entity.Usuario;
import com.example.aplicacionempleos.models.repository.UsuarioRepository;
import com.example.aplicacionempleos.services.interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UsuarioServiceJpa")
public class UsuarioServiceJpa implements IUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
