package br.com.fiap.NaMastreta.controller;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.NaMastreta.models.Curador;
import br.com.fiap.NaMastreta.repository.CuradorReposity;

@RestController
@RequestMapping("api/curador")
public class CuradoriaController {

    // List<Curador> curadores = new ArrayList<>();

    Logger log = LoggerFactory.getLogger(CuradoriaController.class);

    @Autowired
    CuradorReposity repository; // IoD

    @GetMapping
    public List<Curador> listarCuradores() {
        return repository.findAll();
    }

    // C -- CREATE
    @PostMapping
    public ResponseEntity<Curador> create(@RequestBody Curador curador) {
        log.info("cadastrando curador: " + curador);

        repository.save(curador);

        return ResponseEntity.status(HttpStatus.CREATED).body(curador);
    }

    // R —- READ
    @GetMapping("{id}")
    public ResponseEntity<Curador> show(@PathVariable Long id) {
        log.info("buscando despesa com id " + id);

        var curadorEncontrado = repository.findById(id);

        if (curadorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(curadorEncontrado.get());
    }

    // U — UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Curador> update(@PathVariable Long id, @RequestBody Curador curador) {
        log.info("atualizando curador com id " + id);
        var curadorEncontrado = repository.findById(id);

        if (curadorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        curador.setId(id);
        repository.save(curador);

        return ResponseEntity.ok(curador);
    }

    // D — DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Curador> destroy(@PathVariable Long id) {
        log.info("apagando curador com id " + id);
        var curadorEncontrado = repository.findById(id);

        if (curadorEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(curadorEncontrado.get());

        return ResponseEntity.noContent().build();
    }

}
