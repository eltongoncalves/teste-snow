package com.snow.api.model.mapper;

import com.snow.api.model.dto.UsuarioDTO;
import com.snow.api.model.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
