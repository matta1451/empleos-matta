package com.example.aplicacionempleos.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "archivo", length = 255, nullable = false, unique = true)
    private String archivo;
    @Column(name = "comentarios", nullable = true)
    @Type(type = "text")
    private String comentarios;
    @ManyToOne
    @JoinColumn(name = "idVacante")
    private Vacante vacante;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public void actualizarForaneasYFecha(Vacante vacante, Usuario usuario, Date fecha){
        this.vacante = vacante;
        this.usuario = usuario;
        this.fecha = fecha;
    }
}
