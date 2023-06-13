package com.rodrigo.tvShow.repositories;

import com.rodrigo.tvShow.models.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepo extends JpaRepository<Programa, Long> {
}
