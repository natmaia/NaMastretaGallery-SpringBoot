package br.com.fiap.namastreta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Contato;
import br.com.fiap.namastreta.repository.ContatosRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatosRepository contatosRepository;

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContatos() {
        List<Contato> contatos = contatosRepository.findAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatosById(@PathVariable Long id) {
        Contato contato = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));
        return ResponseEntity.ok(contato);
    }

    @PostMapping
    public ResponseEntity<Contato> createContatos(@Valid @RequestBody Contato contatos) {
        Contato createdContatos = contatosRepository.save(contatos);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContatos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContatos(@PathVariable Long id,
            @Valid @RequestBody Contato contatosDetails) {
        Contato contato = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));

        contato.setEmail(contatosDetails.getEmail());
        contato.setSenha(contatosDetails.getSenha());
        contato.setRedeSocial(contatosDetails.getRedeSocial());

        Contato updatedContatos = contatosRepository.save(contato);
        return ResponseEntity.ok(updatedContatos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContatos(@PathVariable Long id) {
        Contato contato = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));

        contatosRepository.delete(contato);
        return ResponseEntity.noContent().build();
    }
}
