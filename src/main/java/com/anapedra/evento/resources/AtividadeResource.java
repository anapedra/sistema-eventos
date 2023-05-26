package com.anapedra.evento.resources;

import com.anapedra.evento.dtos.AtividadeDTO;
import com.anapedra.evento.services.AtividadesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

    private final AtividadesService service;
    public AtividadeResource(AtividadesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AtividadeDTO>> findAll(Pageable pageable) {
        Page<AtividadeDTO> pabe = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(pabe);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AtividadeDTO> findById(@PathVariable Long id) {
        AtividadeDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AtividadeDTO> insert(@RequestBody AtividadeDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AtividadeDTO> update(@PathVariable Long id, @RequestBody AtividadeDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
