package com.snow.catalogoLivros.service;

import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro addLivro(Livro livro) {
        return livroRepository.save(livro);
    }

}
