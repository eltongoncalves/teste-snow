package com.snow.catalogoLivros.repository;

import com.snow.catalogoLivros.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
}
