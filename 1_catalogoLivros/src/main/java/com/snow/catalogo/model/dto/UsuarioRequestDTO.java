package com.snow.catalogo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome do usuario é obrigatório")
    private String nome;

}
