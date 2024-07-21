package com.snow.catalogo.service;

import com.snow.catalogo.exception.BadRequestException;
import com.snow.catalogo.model.entities.Livro;
import com.snow.catalogo.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class LivroService {

    private final LivroRepository livroRepository;
    private final IdiomaService idiomaService;
    private final AutorService autorService;
    private final UsuarioService usuarioService;

    public LivroService(LivroRepository livroRepository, IdiomaService idiomaService, AutorService autorService, UsuarioService usuarioService) {
        this.livroRepository = livroRepository;
        this.idiomaService = idiomaService;
        this.autorService = autorService;
        this.usuarioService = usuarioService;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro adicionar(Livro livro) {

        livro.setIdioma(idiomaService.buscarPorId(livro.getIdioma().getId())
                .orElseThrow(() -> new BadRequestException("Idioma não existe")));

        livro.setAutor(autorService.buscarPorId(livro.getAutor().getId())
                .orElseThrow(() -> new BadRequestException("Autor não existe")));

        livro.setUsuario(usuarioService.buscarPorId(livro.getUsuario().getId())
                .orElseThrow(() -> new BadRequestException("Usuario não existe")));

        return livroRepository.save(livro);
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> atualizar(Long id, Livro livro) {
        return livroRepository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(livro.getTitulo());
                    livroExistente.setIdioma(idiomaService.buscarPorId(livro.getIdioma().getId())
                            .orElseThrow(() -> new BadRequestException("Idioma não existe")));
                    livroExistente.setAutor(autorService.buscarPorId(livro.getAutor().getId())
                            .orElseThrow(() -> new BadRequestException("Autor não existe")));
                    livroExistente.setUsuario(usuarioService.buscarPorId(livro.getUsuario().getId())
                            .orElseThrow(() -> new BadRequestException("Usuario não existe")));
                    return livroRepository.save(livroExistente);
                });
    }

    public String deletar(Long id) {
        return livroRepository.findById(id).map(livro -> {
            livroRepository.delete(livro);
            return ("English".equalsIgnoreCase(livro.getIdioma().getNome())) ?
                    String.format("Book with titled '%s' the on author %s, in English language was deleted.", livro.getTitulo(), livro.getAutor().getNome()):
                    String.format("Livro com título '%s' do autor %s, escrito no idioma %s foi deletado.", livro.getTitulo(), livro.getAutor().getNome(), livro.getIdioma().getNome());
        }).orElseThrow(() -> new BadRequestException("Livro não existe"));
    }

    public List<Livro> findLivrosByAutor(String autor) {
        return livroRepository.findByAutorNome(autor);
    }

    public List<Livro> findLivrosByTitulo(String titulo) {
        return livroRepository.findByTituloContaining(titulo);
    }
}
