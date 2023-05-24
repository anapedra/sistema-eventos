package com.anapedra.evento.repositories;

import com.anapedra.evento.entities.Atividades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividades,Long> {
}
