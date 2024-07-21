package com.snow.catalogo.service;

import com.snow.catalogo.model.Usuario;
import com.snow.catalogo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");
    }

    @Test
    void testListarTodos() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> usuarios = usuarioService.listarTodos();
        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());
        assertEquals("João Silva", usuarios.get(0).getNome());
    }

    @Test
    void testAdicionar() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioSalvo = usuarioService.adicionar(usuario);
        assertNotNull(usuarioSalvo);
        assertEquals("João Silva", usuarioSalvo.getNome());
    }

    @Test
    void testBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> usuarioEncontrado = usuarioService.buscarPorId(1L);
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("João Silva", usuarioEncontrado.get().getNome());
    }

    @Test
    void testAtualizar() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(1L);
        usuarioAtualizado.setNome("Maria Souza");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);

        Optional<Usuario> resultado = usuarioService.atualizar(1L, usuarioAtualizado);
        assertTrue(resultado.isPresent());
        assertEquals("Maria Souza", resultado.get().getNome());
    }

    @Test
    void testDeletar() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.deletar(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}
