package com.snow.catalogoLivros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Idioma é obrigatório")
    private String idioma;

    @NotBlank(message = "Cadastrado por é obrigatório")
    private String cadastradoPor;

}

