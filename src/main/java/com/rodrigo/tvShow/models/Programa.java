package com.rodrigo.tvShow.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "programas", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "network"})})
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El titulo no puede ir en blanco")
    @Size(min = 3, message = "El titulo debe tener al menos 3 caracteres")
    @Column(unique = true)
    private String titulo;
    @NotBlank(message = "La red debe tener al menos 3 caracteres")
    @Size(min = 3, message = "La red debe tener al menos 3 caracteres")
    private String network;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
