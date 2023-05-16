package br.com.fiap.namastreta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.namastreta.models.Credencial;
import br.com.fiap.namastreta.models.Login;
import br.com.fiap.namastreta.repository.LoginRepository;
import jakarta.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    LoginRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/registrar")
    public ResponseEntity<Login> registrar(@RequestBody @Valid Login login){
        login.setSenha(encoder.encode(login.getSenha()));
        repository.save(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(login);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        return ResponseEntity.ok().build();
    }
    
}