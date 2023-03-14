package br.com.fiap.NaMastreta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.NaMastreta.models.Curador;
import br.com.fiap.NaMastreta.models.Login;

@RestController
public class CuradoriaController {
    
    @GetMapping("/api/cadastro-curadoria")
    public Curador show(){
        return new Curador ("Nathalia", new Login("devmaia@outlook.com","4250maia"));
    }
}
