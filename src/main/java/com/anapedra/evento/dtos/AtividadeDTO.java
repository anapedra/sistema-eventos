package com.anapedra.evento.dtos;

import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Participante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AtividadeDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String nome;
    private String preco;
    private Long categoriaId;
    private List<BlocoDTO> blocos=new ArrayList<BlocoDTO>();


    public AtividadeDTO(Long id, String nome, String preco, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoriaId = categoriaId;
    }
    public AtividadeDTO() {

    }
    public AtividadeDTO(Atividade entytie) {
        id = entytie.getId();
        nome = entytie.getNome();
        preco = entytie.getPreco();
        categoriaId = entytie.getCategoria().getId();
        entytie.getBlocos().forEach(bloco -> this.blocos.add(new BlocoDTO(bloco)));

/*


entity.getComments().forEach(comment -> this.comments.add(new CommentDTO(comment)));


    public PostDTO(Post entity, Set<Category> categories,List<Comment>comments) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        comments.forEach(c -> this.comments.add(new CommentDTO(c)));

    }
 */

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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<BlocoDTO> getBlocos() {
        return blocos;
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
