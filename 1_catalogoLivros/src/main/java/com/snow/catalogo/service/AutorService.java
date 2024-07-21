package com.snow.catalogo.service;

import com.snow.catalogo.model.entities.Autor;
import com.snow.catalogo.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor adicionar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Optional<Autor> atualizar(Long id, Autor autor) {
        return autorRepository.findById(id)
                .map(autorExistente -> {
                    autorExistente.setNome(autor.getNome());
                    return autorRepository.save(autorExistente);
                });
    }

    public void deletar(Long id) {
        autorRepository.deleteById(id);
    }
}
