package com.snow.catalogoLivros.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogoLivros.exception.BadRequestException;
import com.snow.catalogoLivros.model.Autor;
import com.snow.catalogoLivros.model.Idioma;
import com.snow.catalogoLivros.model.Livro;
import com.snow.catalogoLivros.model.Usuario;
import com.snow.catalogoLivros.repository.LivroRepository;
import com.snow.catalogoLivros.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    private Livro livro;
    private Autor autor;
    private Idioma idioma;
    private Usuario usuario;

    List<Livro> livros;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setId(1L);
        autor.setNome("Machado de Assis");

        idioma = new Idioma();
        idioma.setId(1L);
        idioma.setNome("Português");
        idioma.setSigla("PT");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João Silva");

        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setAutor(autor);
        livro.setIdioma(idioma);
        livro.setUsuario(usuario);

        livros = Collections.singletonList(livro);

    }

    @Test
    void testListarTodos() throws Exception {
        Mockito.when(livroService.listarTodos()).thenReturn(livros);
        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"))
                .andExpect(jsonPath("$[0].autor.nome").value("Machado de Assis"))
                .andExpect(jsonPath("$[0].idioma.nome").value("Português"))
                .andExpect(jsonPath("$[0].usuario.nome").value("João Silva"));
    }

    @Test
    void testAdicionar() throws Exception {
        Mockito.when(livroService.buscarPorId(1L)).thenReturn(Optional.of(livro));
        Mockito.when(livroService.adicionar(Mockito.any(Livro.class))).thenReturn(livro);

        mockMvc.perform(post("/api/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"))
//                .andExpect(jsonPath("$[0].autor.nome").value("Machado de Assis"))
//                .andExpect(jsonPath("$[0].idioma.nome").value("Português"))
//                .andExpect(jsonPath("$[0].usuario.nome").value("João Silva"));
    }

    @Test
    void testAtualizar() throws Exception {
        Mockito.when(livroService.atualizar(1L, livro)).thenReturn(Optional.of(livro));
        mockMvc.perform(put("/api/livros/{id}", livro.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Dom Casmurro"))
                .andExpect(jsonPath("$.autor.nome").value("Machado de Assis"))
                .andExpect(jsonPath("$.idioma.nome").value("Português"))
                .andExpect(jsonPath("$.usuario.nome").value("João Silva"));
    }

    @Test
    void testAtualizarNotFound() throws Exception {
        mockMvc.perform(put("/api/livros/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletar() throws Exception {
        Mockito.when(livroService.deletar(1L)).thenReturn("Livro com título 'Dom Casmurro' do autor Machado de Assis, escrito no idioma Português foi deletado.");
        mockMvc.perform(delete("/api/livros/{id}", livro.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro com título 'Dom Casmurro' do autor Machado de Assis, escrito no idioma Português foi deletado."));
        Mockito.verify(livroService, Mockito.times(1)).deletar(1L);
    }

    @Test
    void testDeletarNotFound() throws Exception {
        Mockito.when(livroService.deletar(999L)).thenThrow(new BadRequestException("Livro não existe"));
        mockMvc.perform(delete("/api/livros/{id}", 999L))
                .andExpect(status().isBadRequest());
        Mockito.verify(livroService, Mockito.times(1)).deletar(999L);
    }

    @Test
    void testSearchLivrosByAutor() throws Exception {
        List<Livro> livros = Collections.singletonList(livro);
        Mockito.when(livroService.findLivrosByAutor("Machado de Assis")).thenReturn(livros);

        mockMvc.perform(get("/api/livros/search").param("autor", "Machado de Assis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"))
                .andExpect(jsonPath("$[0].autor.nome").value("Machado de Assis"))
                .andExpect(jsonPath("$[0].idioma.nome").value("Português"))
                .andExpect(jsonPath("$[0].usuario.nome").value("João Silva"));
    }

    @Test
    void testSearchLivrosByTitulo() throws Exception {
        List<Livro> livros = Collections.singletonList(livro);
        Mockito.when(livroService.findLivrosByTitulo("Dom Casmurro")).thenReturn(livros);

        mockMvc.perform(get("/api/livros/search").param("titulo", "Dom Casmurro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"))
                .andExpect(jsonPath("$[0].autor.nome").value("Machado de Assis"))
                .andExpect(jsonPath("$[0].idioma.nome").value("Português"))
                .andExpect(jsonPath("$[0].usuario.nome").value("João Silva"));
    }
}
