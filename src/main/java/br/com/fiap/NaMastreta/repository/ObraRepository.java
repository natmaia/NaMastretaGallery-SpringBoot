package br.com.fiap.NaMastreta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.NaMastreta.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long>{
    
}
