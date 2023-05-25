package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Participante;

import java.io.Serializable;
import java.util.Objects;

public class ParticipanteDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String nome;
    private String email;

    public ParticipanteDTO() {

    }

    public ParticipanteDTO(Participante entytie) {
        id=entytie.getId();
        nome=entytie.getNome();
        email=entytie.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteDTO)) return false;
        ParticipanteDTO that = (ParticipanteDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
