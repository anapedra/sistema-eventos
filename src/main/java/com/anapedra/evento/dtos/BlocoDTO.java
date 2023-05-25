package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class BlocoDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private Instant inicio;
    private Instant fim;

    public BlocoDTO(Long id, Instant inicio, Instant fim) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;

    }

    public BlocoDTO() {

    }

    public BlocoDTO(Bloco entytie) {
        id = entytie.getId();
        inicio = entytie.getInicio();
        fim = entytie.getFim();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInicio() {
        return inicio;
    }

    public void setInicio(Instant inicio) {
        this.inicio = inicio;
    }

    public Instant getFim() {
        return fim;
    }

    public void setFim(Instant fim) {
        this.fim = fim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlocoDTO)) return false;
        BlocoDTO blocoDTO = (BlocoDTO) o;
        return Objects.equals(id, blocoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
