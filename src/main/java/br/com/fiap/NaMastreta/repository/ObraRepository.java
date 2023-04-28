package br.com.fiap.namastreta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long>{

    Page<Despesa> findByDescricaoContaining(String descricao, Pageable pageable);
    
}
