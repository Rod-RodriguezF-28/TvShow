package com.rodrigo.tvShow.services;

import com.rodrigo.tvShow.models.Programa;
import com.rodrigo.tvShow.repositories.ProgramaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaService {

    private final ProgramaRepo programaRepo;

    public ProgramaService(ProgramaRepo programaRepo) {
        this.programaRepo = programaRepo;
    }

    public void guardarPrograma(Programa programa) {
        programaRepo.save(programa);
    }

    public void updatePrograma(Programa programa) {
        programaRepo.save(programa);
    }

    public List<Programa> getProgramas() {
        return programaRepo.findAll();
    }

    public Programa getProgramaId(Long id) {
        return programaRepo.findById(id).orElse(null);
    }

    public void deleteProgramaById(Long id) {
        programaRepo.deleteById(id);
    }
}
