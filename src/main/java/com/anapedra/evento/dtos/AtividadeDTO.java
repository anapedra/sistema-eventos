package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.entities.Participante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AtividadeDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String nome;
    private Double preco;
    private CategoriaDTO categoria;
    private List<ParticipanteDTO> participantes=new ArrayList<>();
    private List<BlocoDTO> blocos=new ArrayList<BlocoDTO>();


    public AtividadeDTO(Long id, String nome, Double preco, CategoriaDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public AtividadeDTO() {

    }
    public AtividadeDTO(Atividade entytie) {
        id = entytie.getId();
        nome = entytie.getNome();
        preco=entytie.getPreco();
        categoria = new CategoriaDTO(entytie.getCategoria().getId(),entytie.getCategoria().getDescricao());

    }

    public AtividadeDTO(Atividade entity, Set<Participante> participantes, List<Bloco> blocos) {
        this(entity);
        entity.getParticipantes().forEach(participante -> this.participantes.add(new ParticipanteDTO(participante)));
        entity.getBlocos().forEach(bloco -> this.blocos.add(new BlocoDTO(bloco)));
    }


    public AtividadeDTO(Atividade entity, List<Bloco> blocos) {
        this(entity);
        blocos.forEach(bloco -> this.blocos.add(new BlocoDTO(bloco)));
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public List<BlocoDTO> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<BlocoDTO> blocos) {
        this.blocos = blocos;
    }

    public List<ParticipanteDTO> getParticipantes() {
        return participantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtividadeDTO)) return false;
        AtividadeDTO that = (AtividadeDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
