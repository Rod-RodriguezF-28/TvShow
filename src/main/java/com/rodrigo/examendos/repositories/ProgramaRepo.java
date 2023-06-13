package com.rodrigo.examendos.repositories;

import com.rodrigo.examendos.models.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepo extends JpaRepository<Programa, Long> {
}
