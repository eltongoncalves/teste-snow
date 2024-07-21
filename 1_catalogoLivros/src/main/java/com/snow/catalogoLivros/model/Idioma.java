package com.snow.catalogoLivros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "O nome do idioma é obrigatório")
    private String nome;

//    @NotBlank(message = "A sigla do idioma é obrigatório")
    private String sigla;

}
