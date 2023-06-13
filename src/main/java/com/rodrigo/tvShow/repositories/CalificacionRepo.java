package com.rodrigo.tvShow.repositories;

import com.rodrigo.tvShow.models.Calificacion;
import com.rodrigo.tvShow.models.Programa;
import com.rodrigo.tvShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepo extends JpaRepository<Calificacion, Long> {


    List<Calificacion> findByPrograma(Programa programa);

    boolean existsByUserAndPrograma(User user, Programa programa);



}
