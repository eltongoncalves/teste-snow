package com.snow.catalogo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdiomaRequestDTO {

    @NotBlank(message = "O nome do idioma é obrigatório")
    private String nome;

    @NotBlank(message = "A sigla do idioma é obrigatório")
    private String sigla;
}
