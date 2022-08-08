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
@Table(name = "vacantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    @Type(type = "text")
    private String descripcion;
    @Column(name = "fecha", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "salario", nullable = false)
    private Double salario;
    @Column(name = "destacado", nullable = false)
    private Integer destacado;
    @Column(name = "imagen", nullable = false)
    @Type(type = "text")
    private String imagen = "no-image.png";
    @Column(name = "estatus", length = 50, nullable = false)
    private String estatus;
    @Column(name = "detalles", nullable = true)
    @Type(type = "text")
    private String detalles;
    //@Transient
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public void reset(){
        this.imagen = null;
    }
}
