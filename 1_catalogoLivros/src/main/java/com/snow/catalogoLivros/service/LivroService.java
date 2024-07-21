package com.snow.catalogoLivros.service;

import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private IdiomaService idiomaService;

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro adicionar(Livro livro) {
        return idiomaService.buscarPorId(livro.getIdioma().getId())
                .map(idioma -> {
                    livro.setIdioma(idioma);
                    return livroRepository.save(livro);
                })
                .orElseThrow(() -> new RuntimeException("Idioma não existe"));
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> atualizar(Long id, Livro livro) {
        return livroRepository.findById(id)
                .map(livroExistente -> {
                    idiomaService.buscarPorId(livro.getIdioma().getId())
                            .orElseThrow(() -> new RuntimeException("Idioma não existe"));
                    livroExistente.setTitulo(livro.getTitulo());
                    livroExistente.setAutor(livro.getAutor());
                    livroExistente.setUsuario(livro.getUsuario());
                    livroExistente.setIdioma(livro.getIdioma());
                    return livroRepository.save(livroExistente);
                });
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
