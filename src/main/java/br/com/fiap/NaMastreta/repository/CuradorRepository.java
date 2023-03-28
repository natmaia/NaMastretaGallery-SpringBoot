package br.com.fiap.NaMastreta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.NaMastreta.models.Curador;

public interface CuradorRepository extends JpaRepository<Curador, Long> {
    
}
