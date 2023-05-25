package com.anapedra.evento.services;

import com.anapedra.evento.dtos.AtividadeDTO;
import com.anapedra.evento.dtos.BlocoDTO;
import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.repositories.AtividadeRepository;
import com.anapedra.evento.repositories.BlocoRepository;
import com.anapedra.evento.repositories.CategoriaRepository;
import com.anapedra.evento.services.exceptions.DatabaseException;
import com.anapedra.evento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AtividadesService {

   private final AtividadeRepository atividadeRepository;
   private final BlocoRepository blocoRepository;
   private final CategoriaRepository categoriaRepository;
    public AtividadesService(AtividadeRepository atividadeRepository, BlocoRepository blocoRepository, CategoriaRepository categoriaRepository) {
        this.atividadeRepository = atividadeRepository;
        this.blocoRepository = blocoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public Page<AtividadeDTO> findAllPaged(Pageable pageable) {
        Page<Atividade> page = atividadeRepository.findAll(pageable);
        return page.map(x -> new AtividadeDTO(x,x.getBlocos()));
    }

    @Transactional(readOnly = true)
    public AtividadeDTO findById(Long id) {
        Optional<Atividade> obj = atividadeRepository.findById(id);
        Atividade entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new AtividadeDTO(entity, entity.getBlocos());
    }


    @Transactional
    public AtividadeDTO insert(AtividadeDTO dto) {
       // User user = authService.authenticated();
        try {
            Atividade entity= new Atividade();
            copyDtoToEntity(dto, entity);
            atividadeRepository.save(entity);
            return new AtividadeDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + dto.getCategoriaId());
        }

    }

    @Transactional
    public AtividadeDTO update(Long id, AtividadeDTO dto) {
        try {

            Atividade entity= atividadeRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            atividadeRepository.save(entity);
            return new AtividadeDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            atividadeRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(AtividadeDTO dto, Atividade entity) {

        entity.setNome(dto.getNome());
        entity.setCategoria(categoriaRepository.getReferenceById(dto.getCategoriaId()));
        entity.getBlocos().clear();
        for (BlocoDTO blocoDTO : dto.getBlocos()) {
            Bloco bloco = blocoRepository.getReferenceById(blocoDTO.getId());
            entity.getBlocos().add(bloco);
        }

    }


}



