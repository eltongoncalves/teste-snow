package com.snow.catalogoLivros.service;


import com.snow.catalogoLivros.model.Usuario;
import com.snow.catalogoLivros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario adicionar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> atualizar(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuario.getNome());
                    return usuarioRepository.save(usuarioExistente);
                });
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
