package com.snow.catalogo.repository;

import com.snow.catalogo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE LOWER(l.autor.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Livro> findByAutorNome(@Param("nome") String nome);

    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Livro> findByTituloContaining(@Param("titulo") String titulo);
}
