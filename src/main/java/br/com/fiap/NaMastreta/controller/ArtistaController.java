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

import br.com.fiap.NaMastreta.models.Artista;

@RestController
public class ArtistaController {

    List<Artista> artistas = new ArrayList<Artista>();

    @GetMapping("api/artistas")
    public List<Artista> listarArtistas() {
        return artistas;
    }

    // C - Create
    @PostMapping("api/artista")
    public ResponseEntity<Artista> cadastrar(@RequestBody Artista artista) {

        artista.setId(artistas.size() + 1);

        artistas.add(artista);

        return ResponseEntity.status(HttpStatus.CREATED).body(artista);

    }

    // R - read

    // Get id
    @GetMapping("api/artista/{id}")
    public ResponseEntity<Artista> retornaComId(@PathVariable Integer id) {

        Optional<Artista> artistaContainer = artistas.stream().filter((Artista artista) -> artista.getId().equals(id))
                .findFirst();

        if (artistaContainer.isPresent()) {
            return ResponseEntity.ok(artistaContainer.get());
        }

        // for (Artista a : artistas) {
        // if(a.getId() == id){
        // return ResponseEntity.ok().body(a);
        // }

        // }

        return ResponseEntity.notFound().build();

        // optional é tipo uma lista melhorada
        // stream metodo da lista ->
        // filter - filtra de acordo com a regra que eu criar na lambda
        // -> é o que especifica a Lambda
        // equals valida se é iqual ==
        // findFirst é o primeiro que ele achar

    }

    // U - update
    @PutMapping("api/artista/{id}")
    public ResponseEntity<Artista> updateById(@PathVariable Integer id, @RequestBody Artista art) {

        Optional<Artista> artistaContainer = artistas.stream().filter((Artista artista) -> artista.getId().equals(id))
                .findFirst();

        
        if (artistaContainer.isPresent()) {
            // Artista updateArtista = art;
            // updateArtista.setId(artistaContainer.get().getId());
            // return ResponseEntity.ok(updateArtista);
                 artistaContainer.get().setFoto(art.getFoto());
                 artistaContainer.get().setNome(art.getNome());   
                 
                 return ResponseEntity.ok(artistaContainer.get());   
            
        }
        
        return ResponseEntity.notFound().build();

    }

    // D -delete
    @DeleteMapping("api/artista/{id}")
    public ResponseEntity<Artista> deletaComId(@PathVariable Integer id) {

        Optional<Artista> artistaContainer = artistas.stream().filter((Artista artista) -> artista.getId().equals(id))
                .findFirst();

        if (artistaContainer.isPresent()) {
            artistas.remove(artistaContainer.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

}
