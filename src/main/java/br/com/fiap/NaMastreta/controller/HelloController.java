package br.com.fiap.NaMastreta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value="/")
    public String getnome(){
        return "Olá Mundo";
    }
    
}
