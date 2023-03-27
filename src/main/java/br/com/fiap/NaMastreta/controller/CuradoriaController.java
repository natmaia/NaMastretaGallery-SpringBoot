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


import br.com.fiap.NaMastreta.models.Curador;

@RestController
public class CuradoriaController {

    List<Curador> curadores = new ArrayList<>();

    @GetMapping("api/curador")
    public List<Curador> listarCuradores() {
        return curadores;
    }

    // C -- CREATE
    @PostMapping("api/curador")
    public ResponseEntity<Curador> cadastrarCurador(@RequestBody Curador curador){

        curador.setId(curadores.size() +1);
        curadores.add(curador);

        return ResponseEntity.status(HttpStatus.CREATED).body(curador);
    }

    // R —- READ
    // @GetMapping("api/curador")
    // public Curador show(){
        
    //     return new Curador("foto proficional", "Maia", "Sou aluna de ads", Categoria.ROMANTISMO, null);
    // }

        // read com id
    @GetMapping("api/curador/{id}")
    public ResponseEntity<Curador> retornaCuradorComId(@PathVariable Integer id){

        Optional<Curador> curadorContainer = curadores.stream().filter((Curador Curador) -> Curador.getId().equals(id))
                .findFirst();


        if (curadorContainer.isPresent()){
            return ResponseEntity.ok(curadorContainer.get());
        } return ResponseEntity.notFound().build();

    }


    // U — UPDATE
    @PutMapping("api/curador/{id}")
    public ResponseEntity<Curador> updateById(@PathVariable Integer id, @RequestBody Curador curadoria) {

        Optional<Curador> curadorContainer = curadores.stream().filter((Curador curador) -> curador.getId().equals(id))
                .findFirst();

        if (curadorContainer.isPresent()) {
            curadorContainer.get().setFoto(curadoria.getFoto());
            curadorContainer.get().setNome(curadoria.getNome());
            curadorContainer.get().setTempoAtuacao(curadoria.getDescricao());
            curadorContainer.get().setCategoria(curadoria.getCategoria());
            curadorContainer.get().setDescricao(curadoria.getDescricao());
            curadorContainer.get().setContatos(curadoria.getContatos());
            curadorContainer.get().setId(curadoria.getId());

            return ResponseEntity.ok(curadorContainer.get());

        }
        return ResponseEntity.notFound().build();

    }

    // D — DELETE
    @DeleteMapping("api/curador/{id}")
    public ResponseEntity<Curador> deletaCuradorComId(@PathVariable Integer id){

        Optional<Curador> curadorContainer = curadores.stream().filter((Curador curador) -> curador.getId().equals(id))
                .findFirst();

        if (curadorContainer.isPresent()) {
            curadores.remove(curadorContainer.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

}
