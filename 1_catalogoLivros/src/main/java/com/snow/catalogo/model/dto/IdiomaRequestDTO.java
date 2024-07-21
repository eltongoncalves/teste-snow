package com.snow.catalogo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdiomaRequestDTO {

    @NotBlank(message = "O nome do idioma é obrigatório")
    private String nome;

    @NotBlank(message = "A sigla do idioma é obrigatório")
    private String sigla;
}
