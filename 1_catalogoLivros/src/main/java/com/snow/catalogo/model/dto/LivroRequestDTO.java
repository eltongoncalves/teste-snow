package com.snow.catalogo.model.dto;

import com.snow.catalogo.model.entities.Autor;
import com.snow.catalogo.model.entities.Idioma;
import com.snow.catalogo.model.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private Usuario usuario;
    private Idioma idioma;
    private Autor autor;
}

