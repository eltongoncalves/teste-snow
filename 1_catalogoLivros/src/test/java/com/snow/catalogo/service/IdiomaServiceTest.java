package com.snow.catalogo.service;

import com.snow.catalogo.model.entities.Idioma;
import com.snow.catalogo.repository.IdiomaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdiomaServiceTest {

    @Mock
    private IdiomaRepository idiomaRepository;

    @InjectMocks
    private IdiomaService idiomaService;

    private Idioma idioma;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        idioma = new Idioma();
        idioma.setId(1L);
        idioma.setNome("Inglês");
    }

    @Test
    void testListarTodos() {
        when(idiomaRepository.findAll()).thenReturn(List.of(idioma));
        List<Idioma> idiomas = idiomaService.listarTodos();
        assertFalse(idiomas.isEmpty());
        assertEquals(1, idiomas.size());
        assertEquals("Inglês", idiomas.get(0).getNome());
    }

    @Test
    void testAdicionar() {
        when(idiomaRepository.save(any(Idioma.class))).thenReturn(idioma);
        Idioma idiomaSalvo = idiomaService.adicionar(idioma);
        assertNotNull(idiomaSalvo);
        assertEquals("Inglês", idiomaSalvo.getNome());
    }

    @Test
    void testBuscarPorId() {
        when(idiomaRepository.findById(1L)).thenReturn(Optional.of(idioma));
        Optional<Idioma> idiomaEncontrado = idiomaService.buscarPorId(1L);
        assertTrue(idiomaEncontrado.isPresent());
        assertEquals("Inglês", idiomaEncontrado.get().getNome());
    }

    @Test
    void testAtualizar() {
        Idioma idiomaAtualizado = new Idioma();
        idiomaAtualizado.setId(1L);
        idiomaAtualizado.setNome("Português");

        when(idiomaRepository.findById(1L)).thenReturn(Optional.of(idioma));
        when(idiomaRepository.save(any(Idioma.class))).thenReturn(idiomaAtualizado);

        Optional<Idioma> resultado = idiomaService.atualizar(1L, idiomaAtualizado);
        assertTrue(resultado.isPresent());
        assertEquals("Português", resultado.get().getNome());
    }

    @Test
    void testDeletar() {
        doNothing().when(idiomaRepository).deleteById(1L);
        idiomaService.deletar(1L);
        verify(idiomaRepository, times(1)).deleteById(1L);
    }
}
