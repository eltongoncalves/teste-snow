package com.snow.catalogoLivros.service;

import com.snow.catalogoLivros.model.Autor;
import com.snow.catalogoLivros.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        autor = new Autor();
        autor.setId(1L);
        autor.setNome("J.K. Rowling");
    }

    @Test
    void testListarTodos() {
        when(autorRepository.findAll()).thenReturn(List.of(autor));
        List<Autor> autores = autorService.listarTodos();
        assertFalse(autores.isEmpty());
        assertEquals(1, autores.size());
        assertEquals("J.K. Rowling", autores.get(0).getNome());
    }

    @Test
    void testAdicionar() {
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);
        Autor autorSalvo = autorService.adicionar(autor);
        assertNotNull(autorSalvo);
        assertEquals("J.K. Rowling", autorSalvo.getNome());
    }

    @Test
    void testBuscarPorId() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        Optional<Autor> autorEncontrado = autorService.buscarPorId(1L);
        assertTrue(autorEncontrado.isPresent());
        assertEquals("J.K. Rowling", autorEncontrado.get().getNome());
    }

    @Test
    void testAtualizar() {
        Autor autorAtualizado = new Autor();
        autorAtualizado.setId(1L);
        autorAtualizado.setNome("J.R.R. Tolkien");

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any(Autor.class))).thenReturn(autorAtualizado);

        Optional<Autor> resultado = autorService.atualizar(1L, autorAtualizado);
        assertTrue(resultado.isPresent());
        assertEquals("J.R.R. Tolkien", resultado.get().getNome());
    }

    @Test
    void testDeletar() {
        doNothing().when(autorRepository).deleteById(1L);
        autorService.deletar(1L);
        verify(autorRepository, times(1)).deleteById(1L);
    }
}
