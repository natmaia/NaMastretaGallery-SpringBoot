package br.com.fiap.namastreta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Contatos;

public interface ContatosRepository extends JpaRepository<Contatos, Long> {
}
