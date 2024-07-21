package com.snow.catalogo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogo.model.entities.Idioma;
import com.snow.catalogo.repository.IdiomaRepository;
import com.snow.catalogo.service.IdiomaService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IdiomaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IdiomaService idiomaService;

    @Autowired
    private IdiomaRepository idiomaRepository;

    Idioma idioma = new Idioma();

    @BeforeEach
    void setUp() {
        idioma.setId(1L);
        idioma.setNome("Inglês");
        idioma.setSigla("en-us");
    }

    @Test
    void testListarTodos() throws Exception {

        List<Idioma> idiomas = Collections.singletonList(idioma);
        when(idiomaService.listarTodos()).thenReturn(idiomas);

        mockMvc.perform(get("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(idiomas)));
    }

    @Test
    void testAtualizarNotFound() throws Exception {
        mockMvc.perform(put("/api/idiomas/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idioma)))
                .andExpect(status().isNotFound());
    }


    @Test
    void testAdicionar() throws Exception {
        when(idiomaService.adicionar(idioma)).thenReturn(idioma);
        mockMvc.perform(post("/api/idiomas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idioma)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Inglês"));
        verify(idiomaService, Mockito.times(1)).adicionar(idioma);
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(idiomaService.buscarPorId(1L)).thenReturn(Optional.of(idioma));
        mockMvc.perform(get("/api/idiomas/{id}", idioma.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Inglês"));
        verify(idiomaService, Mockito.times(1)).buscarPorId(1L);
    }

    @Test
    void testBuscarPorIdNotFound() throws Exception {
        mockMvc.perform(get("/api/idiomas/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAtualizar() throws Exception {
        when(idiomaService.atualizar(idioma.getId(), idioma)).thenReturn(Optional.of(idioma));
        mockMvc.perform(put("/api/idiomas/{id}", idioma.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idioma)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Inglês"));
        verify(idiomaService, Mockito.times(1)).atualizar(1L,idioma);
    }


    @Test
    void testDeletar() throws Exception {
        Mockito.doNothing().when(idiomaService).deletar(1L);
        mockMvc.perform(delete("/api/idiomas/{id}", idioma.getId()))
                .andExpect(status().isNoContent());
        verify(idiomaService, Mockito.times(1)).deletar(1L);
    }

    @Test
    void testDeletarNotFound() throws Exception {
        Mockito.doNothing().when(idiomaService).deletar(999L);

        mockMvc.perform(delete("/api/idiomas/{id}", 999L))
                .andExpect(status().isNoContent());

        Mockito.verify(idiomaService, Mockito.times(1)).deletar(999L);
    }


}
