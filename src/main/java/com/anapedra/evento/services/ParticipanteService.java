package com.anapedra.evento.services;

import com.anapedra.evento.dtos.AtividadeDTO;
import com.anapedra.evento.dtos.ParticipanteDTO;
import com.anapedra.evento.entities.Participante;
import com.anapedra.evento.repositories.AtividadeRepository;
import com.anapedra.evento.repositories.ParticipanteRepository;
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
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final AtividadeRepository atividadeRepository;
    public ParticipanteService(ParticipanteRepository participanteRepository, AtividadeRepository atividadeRepository) {
        this.participanteRepository = participanteRepository;
        this.atividadeRepository = atividadeRepository;
    }


    @Transactional(readOnly = true)
    public List<ParticipanteDTO> findAllPaged() {
        List<Participante> list = participanteRepository.findAll();
        return list.stream().map(ParticipanteDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ParticipanteDTO findById(Long id) {
        Optional<Participante> obj = participanteRepository.findById(id);
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ParticipanteDTO(entity, entity.getAtividades());
    }


    @Transactional
    public ParticipanteDTO insert(ParticipanteDTO dto) {
        // User user = authService.authenticated();
        try {
            var entity= new Participante();
            copyDtoToEntity(dto, entity);
            participanteRepository.save(entity);
            return new ParticipanteDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + dto.getId());
        }

    }

    @Transactional
    public ParticipanteDTO update(Long id, ParticipanteDTO dto) {
        try {

            var entity= participanteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            participanteRepository.save(entity);
            return new ParticipanteDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            participanteRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ParticipanteDTO dto, Participante entity) {

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());

        entity.getAtividades().clear();
        for (AtividadeDTO atividadeDTO : dto.getAtividades()) {
            var atividade = atividadeRepository.getReferenceById(atividadeDTO.getId());
            entity.getAtividades().add(atividade);
        }

    }

}
