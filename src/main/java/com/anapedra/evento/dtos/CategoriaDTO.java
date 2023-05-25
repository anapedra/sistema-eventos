package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Categoria;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String descricao;

    public CategoriaDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    public CategoriaDTO() {

    }
    public CategoriaDTO(Categoria entytie) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaDTO)) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
