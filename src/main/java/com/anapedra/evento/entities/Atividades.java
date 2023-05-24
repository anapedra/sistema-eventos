package com.anapedra.evento.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_atividade")
public class Atividades implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String preco;
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    @OneToMany(mappedBy = "atividades")
    private List<Bloco>blocos=new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_atividade_participante",joinColumns = @JoinColumn(name = "atividadeId"),
                inverseJoinColumns = @JoinColumn(name = "participanteId"))
    private Set<Participante>participantes=new HashSet<>();

    public Atividades(Long id, String descricao, String preco, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Atividades() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atividades)) return false;
        Atividades that = (Atividades) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
