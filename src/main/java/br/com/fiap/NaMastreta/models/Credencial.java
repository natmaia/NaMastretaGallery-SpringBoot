package br.com.fiap.namastreta.models;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class Credencial {
    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
    
}