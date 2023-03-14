package br.com.fiap.NaMastreta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.NaMastreta.models.CadastroArtista;

@RestController
public class ArtistaController {

    @GetMapping("/api/cadastro-artista")
    public CadastroArtista show(){
        return new CadastroArtista (1, "Maia", "eu sou nervosa", "sou nervosa");

    }
    
}
