package com.anapedra.evento.repositories;

import com.anapedra.evento.entities.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
}
