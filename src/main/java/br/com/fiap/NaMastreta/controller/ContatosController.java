package br.com.fiap.namastreta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Contatos;
import br.com.fiap.namastreta.repository.ContatosRepository;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatosController {

    @Autowired
    private ContatosRepository contatosRepository;

    @GetMapping
    public ResponseEntity<List<Contatos>> getAllContatos() {
        List<Contatos> contatos = contatosRepository.findAll();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contatos> getContatosById(@PathVariable Long id) {
        Contatos contatos = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));
        return ResponseEntity.ok(contatos);
    }

    @PostMapping
    public ResponseEntity<Contatos> createContatos(@Valid @RequestBody Contatos contatos) {
        Contatos createdContatos = contatosRepository.save(contatos);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContatos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contatos> updateContatos(@PathVariable Long id, @Valid @RequestBody Contatos contatosDetails) {
        Contatos contatos = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));

        contatos.setEmail(contatosDetails.getEmail());
        contatos.setSenha(contatosDetails.getSenha());
        contatos.setRedeSocial(contatosDetails.getRedeSocial());

        Contatos updatedContatos = contatosRepository.save(contatos);
        return ResponseEntity.ok(updatedContatos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContatos(@PathVariable Long id) {
        Contatos contatos = contatosRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Contatos not found with id: " + id));

        contatosRepository.delete(contatos);
        return ResponseEntity.noContent().build();
    }
}
