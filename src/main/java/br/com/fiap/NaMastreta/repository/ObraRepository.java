package br.com.fiap.namastreta.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long> {

    Page<Obra> findByDescricaoContaining(String descricao, Pageable pageable);

    List<Obra> findByArtistaId(Long idArtista);
}
