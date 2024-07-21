package com.snow.catalogoLivros.service;

import com.snow.catalogoLivros.exception.BadRequestException;
import com.snow.catalogoLivros.model.Autor;
import com.snow.catalogoLivros.model.Idioma;
import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.model.Usuario;
import com.snow.catalogoLivros.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private IdiomaService idiomaService;

    @Mock
    private AutorService autorService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private LivroService livroService;

    private Livro livro;
    private Idioma idioma;
    private Autor autor;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        idioma = new Idioma();
        idioma.setId(1L);
        idioma.setNome("Português");

        autor = new Autor();
        autor.setId(1L);
        autor.setNome("Machado de Assis");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");

        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setIdioma(idioma);
        livro.setAutor(autor);
        livro.setUsuario(usuario);
    }

    @Test
    void testListarTodos() {
        when(livroRepository.findAll()).thenReturn(List.of(livro));
        List<Livro> livros = livroService.listarTodos();
        assertFalse(livros.isEmpty());
        assertEquals(1, livros.size());
        assertEquals("Dom Casmurro", livros.get(0).getTitulo());
    }

    @Test
    void testAdicionar() {
        when(idiomaService.buscarPorId(1L)).thenReturn(Optional.of(idioma));
        when(autorService.buscarPorId(1L)).thenReturn(Optional.of(autor));
        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro livroSalvo = livroService.adicionar(livro);
        assertNotNull(livroSalvo);
        assertEquals("Dom Casmurro", livroSalvo.getTitulo());
    }

    @Test
    void testAdicionarIdiomaNaoExiste() {
        when(idiomaService.buscarPorId(1L)).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            livroService.adicionar(livro);
        });

        assertEquals("Idioma não existe", exception.getMessage());
    }

    @Test
    void testBuscarPorId() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        Optional<Livro> livroEncontrado = livroService.buscarPorId(1L);
        assertTrue(livroEncontrado.isPresent());
        assertEquals("Dom Casmurro", livroEncontrado.get().getTitulo());
    }

    @Test
    void testAtualizar() {
        Livro livroAtualizado = new Livro();
        livroAtualizado.setId(1L);
        livroAtualizado.setTitulo("Memórias Póstumas de Brás Cubas");
        livroAtualizado.setIdioma(idioma);
        livroAtualizado.setAutor(autor);
        livroAtualizado.setUsuario(usuario);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(idiomaService.buscarPorId(1L)).thenReturn(Optional.of(idioma));
        when(autorService.buscarPorId(1L)).thenReturn(Optional.of(autor));
        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        when(livroRepository.save(any(Livro.class))).thenReturn(livroAtualizado);

        Optional<Livro> resultado = livroService.atualizar(1L, livroAtualizado);
        assertTrue(resultado.isPresent());
        assertEquals("Memórias Póstumas de Brás Cubas", resultado.get().getTitulo());
    }

    @Test
    void testDeletar() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        doNothing().when(livroRepository).delete(livro);

        String mensagem = livroService.deletar(1L);
        verify(livroRepository, times(1)).delete(livro);
        assertEquals("Livro com título 'Dom Casmurro' do autor Machado de Assis, escrito no idioma Português foi deletado.", mensagem);
    }

    @Test
    void testDeletarLivroNaoExiste() {
        when(livroRepository.findById(1L)).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            livroService.deletar(1L);
        });

        assertEquals("Livro não existe", exception.getMessage());
    }

    @Test
    void testFindLivrosByAutor() {
        when(livroRepository.findByAutorNome("Machado de Assis")).thenReturn(List.of(livro));
        List<Livro> livros = livroService.findLivrosByAutor("Machado de Assis");
        assertFalse(livros.isEmpty());
        assertEquals(1, livros.size());
        assertEquals("Dom Casmurro", livros.get(0).getTitulo());
    }

    @Test
    void testFindLivrosByTitulo() {
        when(livroRepository.findByTituloContaining("Dom")).thenReturn(List.of(livro));
        List<Livro> livros = livroService.findLivrosByTitulo("Dom");
        assertFalse(livros.isEmpty());
        assertEquals(1, livros.size());
        assertEquals("Dom Casmurro", livros.get(0).getTitulo());
    }
}
