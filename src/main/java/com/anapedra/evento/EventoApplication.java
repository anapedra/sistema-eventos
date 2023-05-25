package com.anapedra.evento;

import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.entities.Categoria;
import com.anapedra.evento.entities.Participante;
import com.anapedra.evento.repositories.AtividadeRepository;
import com.anapedra.evento.repositories.BlocoRepository;
import com.anapedra.evento.repositories.CategoriaRepository;
import com.anapedra.evento.repositories.ParticipanteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EventoApplication implements CommandLineRunner {
    private final ParticipanteRepository participanteRepository;
    private final AtividadeRepository atividadeRepository;
    private  final BlocoRepository blocoRepository;
    private final CategoriaRepository categoriaRepository;

    public EventoApplication(ParticipanteRepository participanteRepository, AtividadeRepository atividadeRepository,
                             BlocoRepository blocoRepository, CategoriaRepository categoriaRepository) {
        this.participanteRepository = participanteRepository;
        this.atividadeRepository = atividadeRepository;
        this.blocoRepository = blocoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Atividade>atividades=new ArrayList<>();
        List<Participante>participantes=new ArrayList<>();
        List<Bloco>blocos=new ArrayList<>();
        List<Categoria>categorias=new ArrayList<>();

        Participante p1=new Participante(1L,"José Silva","jose@gmail.com");
        Participante p2=new Participante(2L,"Tiago Faria","tiago@gmail.com");
        Participante p3=new Participante(3L,"Maria do Rosário","maria@gmail.com");
        Participante p4=new Participante(4L,"Tereza Silva","tereza@gmail.com");
        participantes.addAll(Arrays.asList(p1,p2,p3,p4));
        participanteRepository.saveAll(participantes);

        Categoria c1=new Categoria(1L,"Curso");
        Categoria c2=new Categoria(2L,"Oficina");
        categorias.addAll(Arrays.asList(c1,c2));
        categoriaRepository.saveAll(categorias);

        Atividade a1 = new Atividade(1L,"Curso de HTML",80.00,c1);
        Atividade a2 = new Atividade(2L,"Ofina de Github",50.00,c1);
        atividades.addAll(Arrays.asList(a1,a2));
        atividadeRepository.saveAll(atividades);

        Bloco b1 = new Bloco(1L,Instant.parse("2017-09-25T08:00:00Z"),Instant.parse("2017-09-25T11:00:00Z"),a1);
        Bloco b2 = new Bloco(2L,Instant.parse("2017-09-25T14:00:00Z"),Instant.parse("2017-09-25T18:00:00Z"),a2);
        Bloco b3 = new Bloco(3L,Instant.parse("2017-09-26T08:00:00Z"),Instant.parse("2017-09-26T11:00:00Z"),a2);
        Bloco b4 = new Bloco(4L,Instant.now(),Instant.now(),a1);
        blocos.addAll(Arrays.asList(b1,b2,b3,b4));
        blocoRepository.saveAll(blocos);

        a1.getParticipantes().add(p1);
        a1.getParticipantes().add(p2);
        a1.getParticipantes().add(p3);
        a2.getParticipantes().add(p3);
        a2.getParticipantes().add(p4);
        atividades.addAll(Arrays.asList(a1,a2));
        atividadeRepository.saveAll(atividades);

    }

}

