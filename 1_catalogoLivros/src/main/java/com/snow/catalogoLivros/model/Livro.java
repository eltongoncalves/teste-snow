package com.snow.catalogoLivros.model;

import jakarta.persistence.*;
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

    @NotBlank(message = "Cadastrado por é obrigatório")
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

}

