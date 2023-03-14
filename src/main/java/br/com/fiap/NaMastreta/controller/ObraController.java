package br.com.fiap.NaMastreta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.NaMastreta.models.CadastroObra;

@RestController
public class ObraController {
    
    @GetMapping("/api/criar-nova-obra")
    public CadastroObra show(){
        return new CadastroObra ("La creme", "120x150","desenho realista do obra de arte do chocolate la creme");

    }
}

