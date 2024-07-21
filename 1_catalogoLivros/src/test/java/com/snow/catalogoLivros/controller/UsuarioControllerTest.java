package com.snow.catalogoLivros.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.catalogoLivros.model.Usuario;
import com.snow.catalogoLivros.repository.UsuarioRepository;
import com.snow.catalogoLivros.service.UsuarioService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    Usuario usuario = new Usuario();

    @BeforeEach
    void setUp() {
        usuario.setId(1L);
        usuario.setNome("Admin");
    }

    @Test
    void testListarTodos() throws Exception {

        List<Usuario> usuarios = Collections.singletonList(usuario);
        when(usuarioService.listarTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usuarios)));
    }

    @Test
    void testAtualizarNotFound() throws Exception {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Admin");

        mockMvc.perform(put("/api/usuarios/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioAtualizado)))
                .andExpect(status().isNotFound());
    }


    @Test
    void testAdicionar() throws Exception {
        when(usuarioService.adicionar(usuario)).thenReturn(usuario);
        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Admin"));
        verify(usuarioService, Mockito.times(1)).adicionar(usuario);
    }

    @Test
    void testBuscarPorId() throws Exception {
        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        mockMvc.perform(get("/api/usuarios/{id}", usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Admin"));
        verify(usuarioService, Mockito.times(1)).buscarPorId(1L);
    }

    @Test
    void testBuscarPorIdNotFound() throws Exception {
        mockMvc.perform(get("/api/usuarios/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAtualizar() throws Exception {
        when(usuarioService.atualizar(usuario.getId(), usuario)).thenReturn(Optional.of(usuario));
        mockMvc.perform(put("/api/usuarios/{id}", usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Admin"));
        verify(usuarioService, Mockito.times(1)).atualizar(1L,usuario);
    }


    @Test
    void testDeletar() throws Exception {
        Mockito.doNothing().when(usuarioService).deletar(1L);
        mockMvc.perform(delete("/api/usuarios/{id}", usuario.getId()))
                .andExpect(status().isNoContent());
        verify(usuarioService, Mockito.times(1)).deletar(1L);
    }

    @Test
    void testDeletarNotFound() throws Exception {
        Mockito.doNothing().when(usuarioService).deletar(999L);

        mockMvc.perform(delete("/api/usuarios/{id}", 999L))
                .andExpect(status().isNoContent());

        Mockito.verify(usuarioService, Mockito.times(1)).deletar(999L);
    }


}
