package com.rodrigo.tvShow.services;

import com.rodrigo.tvShow.models.Calificacion;
import com.rodrigo.tvShow.models.Programa;
import com.rodrigo.tvShow.models.User;
import com.rodrigo.tvShow.repositories.CalificacionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepo calificacionRepo;

    public CalificacionService(CalificacionRepo calificacionRepo) {
        this.calificacionRepo = calificacionRepo;
    }

    public void insertCalificacion(Calificacion calificacion) {
        calificacionRepo.save(calificacion);
    }

    public List<Calificacion> getCalificacionesByPrograma(Programa programa) {
        return calificacionRepo.findByPrograma(programa);
    }

    public boolean existsByUserAndPrograma(User user, Programa programa) {
        return calificacionRepo.existsByUserAndPrograma(user, programa);

    }


}
