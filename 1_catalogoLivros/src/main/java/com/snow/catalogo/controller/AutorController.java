package com.snow.catalogo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogo.model.dto.AutorRequestDTO;
import com.snow.catalogo.model.entities.Autor;
import com.snow.catalogo.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    private final ObjectMapper objectMapper;

    public AutorController(AutorService autorService, ObjectMapper objectMapper) {
        this.autorService = autorService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.listarTodos();
    }

    @PostMapping
    public Autor adicionar(@Validated @RequestBody AutorRequestDTO autorRequest) {
        var autor = objectMapper.convertValue(autorRequest, Autor.class);
        return autorService.adicionar(autor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Validated @RequestBody Autor autor) {
        return autorService.atualizar(id, autor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
