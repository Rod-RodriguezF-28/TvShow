package com.rodrigo.tvShow.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo rating no puede ser null")
    @DecimalMin(value = "1.0", message = "La rating debe ser mayor o igual a 1.0")
    @DecimalMax(value = "5.0", message = "El rating debe ser menor o igual a 5.0")
    private Double rating;


    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Calificacion() {
    }

    public Calificacion(Long id, Double rating, Programa programa, User user) {
        this.id = id;
        this.rating = rating;
        this.programa = programa;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }
}
