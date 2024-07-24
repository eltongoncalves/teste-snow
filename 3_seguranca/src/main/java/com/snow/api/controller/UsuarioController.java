package com.snow.api.controller;

import com.snow.api.model.dto.UsuarioDTO;
import com.snow.api.model.mapper.UsuarioMapper;
import com.snow.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioMapper usuarioMapper, UsuarioService usuarioService) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsers() {
        return usuarioService.findAll().stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/me")
    public UsuarioDTO getCurrentUser(Principal principal) {
        return usuarioService.findByUsername(principal.getName())
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

