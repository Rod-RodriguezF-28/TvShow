package com.rodrigo.examendos.repositories;

import com.rodrigo.examendos.models.Calificacion;
import com.rodrigo.examendos.models.Programa;
import com.rodrigo.examendos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepo extends JpaRepository<Calificacion, Long> {


    List<Calificacion> findByPrograma(Programa programa);

    boolean existsByUserAndPrograma(User user, Programa programa);



}
