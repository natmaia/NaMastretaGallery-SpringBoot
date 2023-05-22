package br.com.fiap.namastreta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long> {

    Page<Obra> findByDescricaoContaining(String descricao, org.springframework.data.domain.Pageable pageable);

}
