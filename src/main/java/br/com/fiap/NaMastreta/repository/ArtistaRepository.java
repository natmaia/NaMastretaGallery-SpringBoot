package br.com.fiap.namastreta.repository;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{

    Page<Artista> findByName(String nome, org.springframework.data.domain.Pageable pageable);
    //Page<Artista> findByName(String nome, Pageable pageable);

}