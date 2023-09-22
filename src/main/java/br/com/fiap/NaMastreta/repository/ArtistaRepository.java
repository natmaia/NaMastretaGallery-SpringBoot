package br.com.fiap.namastreta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Page<Artista> findByNome(String nome, Pageable pageable);

}