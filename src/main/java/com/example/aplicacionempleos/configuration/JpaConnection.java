package com.example.aplicacionempleos.configuration;

import com.example.aplicacionempleos.models.entity.Usuario;
import com.example.aplicacionempleos.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("JpaConnection")
public class JpaConnection implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        boolean activo = usuario.getEstatus() == 1;
        usuario.getPerfiles().forEach(e -> {
            roles.add(new SimpleGrantedAuthority(e.getPerfil()));
        });
        return new User(usuario.getUsername(), usuario.getPassword(), activo, true, true, true, roles);
    }
}
