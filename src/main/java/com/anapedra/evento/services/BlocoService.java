package com.anapedra.evento.services;

import com.anapedra.evento.dtos.AtividadeDTO;
import com.anapedra.evento.dtos.BlocoDTO;
import com.anapedra.evento.entities.Atividade;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.repositories.BlocoRepository;
import com.anapedra.evento.services.exceptions.DatabaseException;
import com.anapedra.evento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlocoService {

    private final BlocoRepository blocoRepository;
    public BlocoService(BlocoRepository blocoRepository) {
        this.blocoRepository = blocoRepository;
    }

    @Transactional(readOnly = true)
    public List<BlocoDTO> findAllPaged() {
        List<Bloco> list = blocoRepository.findAll();
        return list.stream().map(BlocoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BlocoDTO findById(Long id) {
        Optional<Bloco> obj = blocoRepository.findById(id);
        Bloco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new BlocoDTO(entity);
    }


    @Transactional
    public BlocoDTO insert(BlocoDTO dto) {
        // User user = authService.authenticated();
        try {
            var entity= new Bloco();
            entity.setFim(dto.getInicio());
            entity.setFim(dto.getFim());
            blocoRepository.save(entity);
            return new BlocoDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + dto.getId());
        }

    }

    @Transactional
    public BlocoDTO update(Long id, BlocoDTO dto) {
        try {
            Bloco entity= blocoRepository.getReferenceById(id);
            entity.setFim(dto.getInicio());
            entity.setFim(dto.getFim());
            blocoRepository.save(entity);
            return new BlocoDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            blocoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }



}
