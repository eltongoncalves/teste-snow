package com.snow.catalogoLivros.controller;

import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
//    @ApiOperation(value = "Listar todos os livros")
    public List<Livro> getAllLivros() {
        return livroService.getAllLivros();
    }

    @PostMapping
//    @ApiOperation(value = "Adicionar um novo livro")
    public Livro addLivro(@Validated @RequestBody Livro livro) {
        return livroService.addLivro(livro);
    }

}
