package com.snow.catalogo.controller;

import com.snow.catalogo.model.entities.Livro;
import com.snow.catalogo.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @PostMapping
    public Livro adicionar(@RequestBody Livro livro) {
        return livroService.adicionar(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Validated @RequestBody Livro livroDetails) {
        Optional<Livro> updatedLivro = livroService.atualizar(id, livroDetails);
        return updatedLivro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.deletar(id));
    }

    @GetMapping("/search")
    public List<Livro> searchLivros(@RequestParam(required = false) String autor, @RequestParam(required = false) String titulo) {
        if (autor != null) {
            return livroService.findLivrosByAutor(autor);
        } else if (titulo != null) {
            return livroService.findLivrosByTitulo(titulo);
        }
        return new ArrayList<>();
    }
}
