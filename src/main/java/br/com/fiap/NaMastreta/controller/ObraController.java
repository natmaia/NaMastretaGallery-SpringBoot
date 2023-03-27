package br.com.fiap.NaMastreta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.NaMastreta.models.Obra;

@RestController
public class ObraController {

    List<Obra> obras = new ArrayList<>();

    @GetMapping("/api/obra")
    public List<Obra> listarObras() {
        return obras;
    }

    // C —- CREATE

    @PostMapping("/api/obra")
    public ResponseEntity<Obra> cadastrarObra(@RequestBody Obra obra) {

        obra.setId(obra.size() + 1);

        obras.add(obra);

        return ResponseEntity.status(HttpStatus.CREATED).body(obra);

    }

    // R —- READ

    // @GetMapping("/api/obra")
    // public Obra show() {

    //     return new Obra("foto", "desespero", "foto de um aluno da Fiap", "20x10", null, null, null) {

    //     };

    // }

    @GetMapping("/api/obra/{id}")
    public ResponseEntity<Obra> retornaObraComId(@PathVariable Integer id) {

        Optional<Obra> obraContainer = obras.stream().filter((Obra obra) -> obra.getId().equals(id))
                .findFirst();

        if (obraContainer.isPresent()) {
            return ResponseEntity.ok(obraContainer.get());
        }
        return ResponseEntity.notFound().build();
    }

    // U — UPDATE

    @PutMapping("/api/obra/{id}")
    public ResponseEntity<Obra> updateObraById(@PathVariable Integer id, @RequestBody Obra obrinha) {

        Optional<Obra> obraContainer = obras.stream().filter((Obra obra) -> obra.getId().equals(id))
                .findFirst();

        if (obraContainer.isPresent()) {
            obraContainer.get().setFoto(obrinha.getFoto());
            obraContainer.get().setNome(obrinha.getNome());
            obraContainer.get().setDescricao(obrinha.getDescricao());
            obraContainer.get().setTamanho(obrinha.getTamanho());
            obraContainer.get().setCurador(obrinha.getCurador());
            obraContainer.get().setArtista(obrinha.getArtista());
            obraContainer.get().setValor(obrinha.getValor());
            obraContainer.get().setId(obrinha.getId());

            return ResponseEntity.ok(obraContainer.get());

        }
        return ResponseEntity.notFound().build();

    }

    // D — DELETE

    @DeleteMapping("/api/obra/{id}")
    public ResponseEntity<Obra> deletaObraComId(@PathVariable Integer id) {

        Optional<Obra> obraContainer = obras.stream().filter((Obra obra) -> obra.getId().equals(id))
                .findFirst();

        if (obraContainer.isPresent()) {
            obras.remove(obraContainer.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }
}
