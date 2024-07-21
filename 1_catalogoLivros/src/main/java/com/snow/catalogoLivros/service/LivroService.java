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

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro adicionar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> atualizar(Long id, Livro livroDetails) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            Livro updatedLivro = livro.get();
            updatedLivro.setTitulo(livroDetails.getTitulo());
            updatedLivro.setAutor(livroDetails.getAutor());
            updatedLivro.setIdioma(livroDetails.getIdioma());
            updatedLivro.setCadastradoPor(livroDetails.getCadastradoPor());
            return Optional.of(livroRepository.save(updatedLivro));
        }
        return Optional.empty();
    }

    public Optional<Livro> deletar(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        livro.ifPresent(livroRepository::delete);
        return livro;
    }

    public List<Livro> findLivrosByAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    public List<Livro> findLivrosByTitulo(String titulo) {
        return livroRepository.findByTituloContaining(titulo);
    }
}
