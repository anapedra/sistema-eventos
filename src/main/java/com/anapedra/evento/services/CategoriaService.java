package com.anapedra.evento.services;

import com.anapedra.evento.dtos.BlocoDTO;
import com.anapedra.evento.dtos.CategoriaDTO;
import com.anapedra.evento.entities.Bloco;
import com.anapedra.evento.entities.Categoria;
import com.anapedra.evento.repositories.CategoriaRepository;
import com.anapedra.evento.services.exceptions.DatabaseException;
import com.anapedra.evento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAllPaged() {
        List<Categoria> list = categoriaRepository.findAll();
        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoriaDTO(entity);
    }


    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        // User user = authService.authenticated();
        try {
            var entity= new Categoria();
            entity.setDescricao(dto.getDescricao());
           categoriaRepository.save(entity);
            return new CategoriaDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + dto.getId());
        }

    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity= categoriaRepository .getReferenceById(id);
            entity.setDescricao(dto.getDescricao());
            categoriaRepository.save(entity);
            return new CategoriaDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            categoriaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }



}
