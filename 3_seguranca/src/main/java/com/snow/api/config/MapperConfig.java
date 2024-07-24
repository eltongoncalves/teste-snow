package com.snow.api.config;

import com.snow.api.model.mapper.UsuarioMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UsuarioMapper usuarioMapper() {
        return Mappers.getMapper(UsuarioMapper.class);
    }
}
