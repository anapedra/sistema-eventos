package com.anapedra.evento.resources;

import com.anapedra.evento.dtos.AtividadeDTO;
import com.anapedra.evento.dtos.BlocoDTO;
import com.anapedra.evento.services.BlocoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/blocos")
public class BlocoResource {

    private final BlocoService service;
    public BlocoResource(BlocoService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<BlocoDTO>> findAll() {
        List<BlocoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BlocoDTO> findById(@PathVariable Long id) {
        BlocoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<BlocoDTO> insert(@RequestBody BlocoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BlocoDTO> update(@PathVariable Long id, @RequestBody BlocoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


