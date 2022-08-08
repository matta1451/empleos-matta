package com.example.aplicacionempleos.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "nombre", length = 50, nullable = false, unique = false)
    private String nombre;
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;
    @Column(name = "password", length = 150, nullable = false, unique = false)
    private String password;
    @Column(name = "estatus", nullable = false)
    private byte estatus;
    @Column(name = "fechaRegistro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarioperfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;

    public void savePerfil(Perfil perfil){
        if(this.perfiles == null){
            this.perfiles = new ArrayList<>();
        }
        this.perfiles.add(perfil);
    }
}
