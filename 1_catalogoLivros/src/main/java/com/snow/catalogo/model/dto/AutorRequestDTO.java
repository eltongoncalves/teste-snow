package com.snow.catalogo.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorRequestDTO {

    @NotBlank(message = "O nome do autor é obrigatório")
    private String nome;

}
