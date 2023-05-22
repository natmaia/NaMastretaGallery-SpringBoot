package br.com.fiap.namastreta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByEmail(String email);

}

