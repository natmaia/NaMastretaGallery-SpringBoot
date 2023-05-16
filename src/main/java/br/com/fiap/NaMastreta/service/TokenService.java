package br.com.fiap.namastreta.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.namastreta.models.Credencial;
import br.com.fiap.namastreta.models.Token;
import br.com.fiap.namastreta.models.Login;
import br.com.fiap.namastreta.repository.LoginRepository;
import jakarta.validation.Valid;

@Service
public class TokenService {

    @Autowired
    LoginRepository loginRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(@Valid Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                    .withSubject(credencial.email())
                    .withIssuer("MeuJulius")
                    .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .sign(alg)
                    ;
        return new Token(token, "JWT", "Bearer");
    }

    public Login getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                    .withIssuer("MeuJulius")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;

        return loginRepository.findByEmail(email)
                    .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }


    
}