package com.snow.catalogo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AutorRequestDTO {

    @NotBlank(message = "O nome do autor é obrigatório")
    private String nome;

}
