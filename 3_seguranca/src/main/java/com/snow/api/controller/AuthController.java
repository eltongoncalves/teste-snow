package com.snow.api.controller;

import com.snow.api.model.dto.AuthRequestDTO;
import com.snow.api.model.entity.Usuario;
import com.snow.api.security.JwtTokenProvider;
import com.snow.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public String login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            String username = authRequestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequestDTO.getPassword()));
            Usuario usuario = usuarioService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            return jwtTokenProvider.createToken(username, usuario.getRoles());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/signup")
    public Usuario signup(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
}

