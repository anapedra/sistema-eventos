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
    private String nome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    @OneToMany(mappedBy = "atividades")
    private List<Bloco>blocos=new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_atividade_categoria",joinColumns = @JoinColumn(name = "atividadeId"),
                inverseJoinColumns = @JoinColumn(name = "categoriaId"))
    private Set<Participante>participantes=new HashSet<>();

    public Atividades(Long id, String nome, String email, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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
