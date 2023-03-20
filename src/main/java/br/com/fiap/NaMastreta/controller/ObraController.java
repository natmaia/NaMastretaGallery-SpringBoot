package br.com.fiap.NaMastreta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.NaMastreta.models.Obra;

@RestController
public class ObraController {
    
    @GetMapping("/api/obra")
    public Obra show(){
        return new Obra ("La creme", "120x150","desenho realista do obra de arte do chocolate la creme");

    }
}

