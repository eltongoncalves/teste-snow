package com.snow.catalogoLivros.controller;

import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

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
        Optional<Livro> livro = livroService.deletar(id);
        if (livro.isPresent()) {
            Livro deletedLivro = livro.get();
            String message = String.format("Livro com título '%s' em %s idioma foi deletado.", deletedLivro.getTitulo(), deletedLivro.getIdioma());
            if ("English".equalsIgnoreCase(deletedLivro.getIdioma().getNome())) {
                message = String.format("Book titled '%s' in English language was deleted.", deletedLivro.getTitulo());
            }
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
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
