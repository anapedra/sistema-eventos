package com.anapedra.evento.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_bloco")
public class Bloco implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant inicio;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant fim;
    @ManyToOne
    @JoinColumn(name = "atividadesId")
    private Atividade atividades;

    public Bloco(Long id, Instant inicio, Instant fim, Atividade atividades) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.atividades = atividades;
    }

    public Bloco() {

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

    public Atividade getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividade atividades) {
        this.atividades = atividades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bloco)) return false;
        Bloco bloco = (Bloco) o;
        return Objects.equals(getId(), bloco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
