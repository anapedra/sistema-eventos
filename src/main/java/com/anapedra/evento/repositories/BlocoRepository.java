package com.anapedra.evento.repositories;

import com.anapedra.evento.entities.Bloco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlocoRepository extends JpaRepository<Bloco,Long> {
}
/*
INSERT INTO tb_participante(nome, email) VALUES ('José Silva', 'jose@gmail.com');
INSERT INTO tb_participante(nome, email) VALUES ('Tiago Faria', 'tiago@gmail.com');
INSERT INTO tb_participante(nome, email) VALUES ('Maria do Rosário', 'maria@gmail.com');
INSERT INTO tb_participante(nome, email) VALUES ('Tereza Silva', 'tereza@gmail.com');

INSERT INTO tb_categoria(descricao) VALUES (Curso);
INSERT INTO tb_categoria(descricao) VALUES (Oficina);

INSERT INTO tb_atividade(nome,preco,categoriaId) VALUES (Curso_de_HTML,80.00,1);
INSERT INTO tb_atividade(nome,preco,categoriaId) VALUES (Ofina_de_Github,50.00,2);

INSERT INTO tb_bloco(inicio,fim,atividade_id) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-25T08:00:00Z',TIMESTAMP WITH TIME ZONE '2017-09-25T11:00:00Z',1);
INSERT INTO tb_bloco(inicio,fim,atividade_id) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-25T14:00:00Z',TIMESTAMP WITH TIME ZONE '2017-09-25T18:00:00Z',2);
INSERT INTO tb_bloco(inicio,fim,atividade_id) VALUES (TIMESTAMP WITH TIME ZONE '2017-09-26T08:00:00Z',TIMESTAMP WITH TIME ZONE '2017-09-26T11:00:00Z',2);

INSERT INTO tb_atividade_participante (atividade_id, participante_id) VALUES (1, 1);
INSERT INTO tb_atividade_participante (atividade_id, participante_id) VALUES (1, 2);
INSERT INTO tb_atividade_participante (atividade_id, participante_id) VALUES (1, 3);
INSERT INTO tb_atividade_participante (atividade_id, participante_id) VALUES (2, 3);
INSERT INTO tb_atividade_participante (atividade_id, participante_id) VALUES (2, 4);

 */
