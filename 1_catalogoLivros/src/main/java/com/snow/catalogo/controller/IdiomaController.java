package com.snow.catalogo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogo.model.dto.IdiomaRequestDTO;
import com.snow.catalogo.model.entities.Idioma;
import com.snow.catalogo.service.IdiomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/idiomas")
public class IdiomaController {

    private final IdiomaService idiomaService;

    private final ObjectMapper objectMapper;

    public IdiomaController(IdiomaService idiomaService, ObjectMapper objectMapper) {
        this.idiomaService = idiomaService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Idioma> listarTodos() {
        return idiomaService.listarTodos();
    }

    @PostMapping
    public Idioma adicionar(@Validated @RequestBody IdiomaRequestDTO idiomaRequest) {
        var idioma = objectMapper.convertValue(idiomaRequest, Idioma.class);
        return idiomaService.adicionar(idioma);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Idioma> buscarPorId(@PathVariable Long id) {
        return idiomaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Idioma> atualizar(@PathVariable Long id, @Validated @RequestBody Idioma idioma) {
        return idiomaService.atualizar(id, idioma)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        idiomaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
