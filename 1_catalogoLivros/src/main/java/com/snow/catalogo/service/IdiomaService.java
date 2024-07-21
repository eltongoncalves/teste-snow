package com.snow.catalogo.service;

import com.snow.catalogo.model.Idioma;
import com.snow.catalogo.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class IdiomaService {

    private final IdiomaRepository idiomaRepository;

    @Autowired
    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    public List<Idioma> listarTodos() {
        return idiomaRepository.findAll();
    }

    public Idioma adicionar(Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    public Optional<Idioma> buscarPorId(Long id) {
        return idiomaRepository.findById(id);
    }

    public Optional<Idioma> atualizar(Long id, Idioma idioma) {
        return idiomaRepository.findById(id)
                .map(idiomaExistente -> {
                    idiomaExistente.setNome(idioma.getNome());
                    return idiomaRepository.save(idiomaExistente);
                });
    }

    public void deletar(Long id) {
        idiomaRepository.deleteById(id);
    }
}
