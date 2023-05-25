package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.entities.Participante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ParticipanteDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String nome;
    private String email;
    private List<AtividadeDTO>atividades=new ArrayList<>();

    public ParticipanteDTO() {

    }

    public ParticipanteDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;

    }

    public ParticipanteDTO(Participante entytie) {
        id=entytie.getId();
        nome=entytie.getNome();
        email=entytie.getEmail();
        entytie.getAtividades().forEach(atividade -> this.atividades.add(new AtividadeDTO(atividade)));
    }
    public ParticipanteDTO(Participante entity, Set<Atividade> atividades) {
        this(entity);
        atividades.forEach(atividade -> this.atividades.add(new AtividadeDTO(atividade)));
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

    public List<AtividadeDTO> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeDTO> atividades) {
        this.atividades = atividades;
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
