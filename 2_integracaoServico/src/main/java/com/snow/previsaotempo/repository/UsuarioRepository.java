package com.snow.previsaotempo.repository;

import com.snow.previsaotempo.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
