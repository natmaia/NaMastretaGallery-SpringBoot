package br.com.fiap.namastreta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.repository.CuradorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/curador")
public class CuradorController {

    Logger log = LoggerFactory.getLogger(CuradorController.class);

    @Autowired
    CuradorRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;


    // @GetMapping
    // public Page<Curador> index(@RequestParam(required=false) String nome , @PageableDefault() Pageable pageable){
    //     if (nome == null)
    //         return repository.findAll(pageable);

    //     return repository.findByName(nome, pageable);
    // }

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String nome, @PageableDefault() Pageable pageable) {
        Page<Curador> curadores = (nome == null)?
            repository.findAll(pageable):
<<<<<<< HEAD
            repository.findByNome(nome, pageable);
=======
            repository.findByName(nome, pageable);
>>>>>>> 14f8cf990b328dc6d28d839d71be8aba52fe437a

        return assembler.toModel(curadores.map(Curador::toEntityModel));
    }
  
    // C -- CREATE
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Curador curador) {
        log.info("cadastrando curador: " + curador);
        repository.save(curador);
<<<<<<< HEAD
        return ResponseEntity
                .created(curador.toEntityModel().getRequiredLink("self").toUri())
                .body(curador.toEntityModel());
=======
        return ResponseEntity.created(despesa.toEntityModel().getRequiredLink("self").toUri()).body(despesa.toEntityModel());
>>>>>>> 14f8cf990b328dc6d28d839d71be8aba52fe437a
    }

    // R —- READ
    @GetMapping("{id}")
    public EntityModel<Curador> show(@PathVariable Long id) {
        log.info("buscando curador com id " + id);
<<<<<<< HEAD
=======
        // var curadorEncontrado = repository.findById(id);
        // if (curadorEncontrado.isEmpty())
        // return ResponseEntity.notFound().build();
>>>>>>> 14f8cf990b328dc6d28d839d71be8aba52fe437a

        return getCurador(id).toEntityModel();
    }

    // U — UPDATE
    @PutMapping("{id}")
    public EntityModel<Curador> update(@PathVariable Long id, @RequestBody @Valid Curador curador) {
        log.info("atualizando curador com id " + id);
        var curadorEncontrado = getCurador(id);

        curador.setId(curadorEncontrado.getId());
        repository.save(curador);

        return curador.toEntityModel();
    }

    // D — DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Curador> destroy(@PathVariable Long id) {
        log.info("apagando curador com id " + id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(getCurador(id));
       
    }

    private Curador getCurador (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrado"));
    }

}