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
import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.repository.ObraRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/obra")
public class ObraController {

    // List<Obra> obras = new ArrayList<>();
    Logger log = LoggerFactory.getLogger(ObraController.class);

    @Autowired
    ObraRepository repository;


    @Autowired
    PagedResourcesAssembler<Object> assembler;

<<<<<<< HEAD
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String descricao, @PageableDefault(size = 5) Pageable pageable) {
        Page<Obra> obras = (descricao == null)?
            repository.findAll(pageable):
            repository.findByDescricaoContaining(descricao, pageable);

        return assembler.toModel(obras.map(Obra::toEntityModel));
    }
=======

    // @GetMapping
    // public Page<Obra> index(@RequestParam(required=false) String descricao, @PageableDefault(size =5) Pageable pageable){
    //     if (descricao == null)
    //         return repository.findAll(pageable);

    //     return repository.findByDescricaoContaining(descricao, pageable);
    // }
    
>>>>>>> 14f8cf990b328dc6d28d839d71be8aba52fe437a

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String descricao, @PageableDefault(size = 5) Pageable pageable) {
        Page<Obra> obras = (descricao == null)?
            repository.findAll(pageable):
            repository.findByDescricaoContaining(descricao, pageable);

        return assembler.toModel(obras.map(Obra::toEntityModel));
    }




    // C —- CREATE

    @PostMapping
    public ResponseEntity<EntityModel<Obra>> cadastrarObra(@RequestBody @Valid Obra obra) {

        log.info("Cadastrando a obrinha: " + obra);

        repository.save(obra);

        return ResponseEntity
                .created(obra.toEntityModel().getRequiredLink("self").toUri())
                .body(obra.toEntityModel());

    }
        
    // R —- READ
    @GetMapping("{id}")
    public EntityModel<Obra> retornaObraComId(@PathVariable Long id) {

        log.info("Buscando Obra por id: " + id);
        
        return getObra(id).toEntityModel();
    }

    // U — UPDATE

    @PutMapping("{id}")
    public EntityModel<Obra> update(@PathVariable Long id, @RequestBody @Valid Obra obra) {
        log.info("atualizando despesa com id " + id);
        var obraEncontrada = getObra(id);

        obra.setId(obraEncontrada.getId());
        repository.save(obra);

        return obra.toEntityModel();
    }

    // D — DELETE

    @DeleteMapping("{id}")
    public ResponseEntity<Obra> deletaObraComId(@PathVariable Long id) {

        log.info("apagando obra através do id " + id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(getObra(id));

    }

    private Obra getObra (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrado"));
    }

    public Class<?> show(Long long1) {
        return null;
    }

    public Object destroy(Long id) {
        return null;
    }

}

    // -----  Entender como faz a versão optional com spring boot JPA -----

    //Alterei tudo para variavel, até entender melhor a estrutura do optional

                        /*
                        * Optional<Obra> obraContainer = obras.stream().filter((Obra obra) ->
                        * obra.getId().equals(id))
                        * .findFirst();
                        * 
                        * if (obraContainer.isPresent()) {
                        * return ResponseEntity.ok(obraContainer.get());
                        * }
                        */