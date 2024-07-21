package com.snow.catalogoLivros.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogoLivros.model.Autor;
import com.snow.catalogoLivros.repository.AutorRepository;
import com.snow.catalogoLivros.service.AutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AutorService autorService;

    @Autowired
    private AutorRepository autorRepository;

    Autor autor = new Autor();

    @BeforeEach
    void setUp() {
        autor.setId(1L);
        autor.setNome("Jorge Amado");
    }

    @Test
    void testListarTodos() throws Exception {

        List<Autor> autores = Collections.singletonList(autor);
        when(autorService.listarTodos()).thenReturn(autores);

        mockMvc.perform(get("/api/autores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(autores)));
    }

    @Test
    void testAtualizarNotFound() throws Exception {
        Autor autorAtualizado = new Autor();
        autorAtualizado.setNome("Jorge Amado");

        mockMvc.perform(put("/api/autores/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autorAtualizado)))
                .andExpect(status().isNotFound());
    }


    @Test
    void testAdicionar() throws Exception {
        when(autorService.adicionar(autor)).thenReturn(autor);
        mockMvc.perform(post("/api/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Jorge Amado"));
        verify(autorService, Mockito.times(1)).adicionar(autor);
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(autorService.buscarPorId(1L)).thenReturn(Optional.of(autor));
        mockMvc.perform(get("/api/autores/{id}", autor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Jorge Amado"));
        verify(autorService, Mockito.times(1)).buscarPorId(1L);
    }

    @Test
    void testBuscarPorIdNotFound() throws Exception {
        mockMvc.perform(get("/api/autores/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAtualizar() throws Exception {
        when(autorService.atualizar(autor.getId(), autor)).thenReturn(Optional.of(autor));
        mockMvc.perform(put("/api/autores/{id}", autor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Jorge Amado"));
        verify(autorService, Mockito.times(1)).atualizar(1L,autor);
    }


    @Test
    void testDeletar() throws Exception {
        Mockito.doNothing().when(autorService).deletar(1L);
        mockMvc.perform(delete("/api/autores/{id}", autor.getId()))
                .andExpect(status().isNoContent());
        verify(autorService, Mockito.times(1)).deletar(1L);
    }

    @Test
    void testDeletarNotFound() throws Exception {
        Mockito.doNothing().when(autorService).deletar(999L);

        mockMvc.perform(delete("/api/autores/{id}", 999L))
                .andExpect(status().isNoContent());

        Mockito.verify(autorService, Mockito.times(1)).deletar(999L);
    }


}
