package br.com.fiap.namastreta.repository;

import java.util.Optional;

//import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.namastreta.models.Login;
//import jakarta.validation.Valid;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByEmail(String email);

}

