package com.anapedra.evento.resources;

import com.anapedra.evento.dtos.ParticipanteDTO;
import com.anapedra.evento.services.ParticipanteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/participantes")
public class ParticipanteResoruce {

    private final ParticipanteService service;
    public ParticipanteResoruce(ParticipanteService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<ParticipanteDTO>> findAll(){
        List<ParticipanteDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipanteDTO> findById(@PathVariable Long id) {
        ParticipanteDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ParticipanteDTO> insert(@RequestBody ParticipanteDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ParticipanteDTO> update(@PathVariable Long id, @RequestBody ParticipanteDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


