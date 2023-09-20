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

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.repository.CuradorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/curador")
public class CuradorController {

    Logger log = LoggerFactory.getLogger(CuradorController.class);

    @Autowired
    CuradorRepository repository;

    @Autowired
    PagedResourcesAssembler<Curador> assembler;

    
    @GetMapping
    public PagedModel<EntityModel<Curador>> index(@RequestParam(required = false) String nome, @PageableDefault() Pageable pageable) {
        Page<Curador> curadores = (nome == null) ?
        repository.findAll(pageable) :
        repository.findByNome(nome, pageable);
        
        return assembler.toModel(curadores, entity -> entity.toEntityModel());
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "despesa cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "dados inválidos, a validação falhou")
    })
    public ResponseEntity<EntityModel<Curador>> create(@RequestBody @Valid Curador curador) {
        log.info("Cadastrando curador: " + curador);
        Curador savedCurador = repository.save(curador);
        
        return ResponseEntity
                .created(savedCurador.toEntityModel().getRequiredLink("self").toUri())
                .body(savedCurador.toEntityModel());
            }
            
    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes da despesa",
        description = "Retorna os dados de uma despesa passada pelo parâmetro de path id"
        )
        public EntityModel<Curador> show(@PathVariable Long id) {
            log.info("Buscando curador por id: " + id);
            Curador curador = getCurador(id);
            return curador.toEntityModel();
        }
        
        @PutMapping("{id}")
        public ResponseEntity<EntityModel<Curador>> update(@PathVariable Long id, @RequestBody @Valid Curador curador) {
            log.info("Atualizando curador com id: " + id);
            
            Curador curadorEncontrado = getCurador(id);
            
        curadorEncontrado.setFoto(curador.getFoto());
        curadorEncontrado.setNome(curador.getNome());
        curadorEncontrado.setDescricao(curador.getDescricao());
        
        Curador updatedCurador = repository.save(curadorEncontrado);
        
        return ResponseEntity.ok(updatedCurador.toEntityModel());
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        log.info("Apagando curador com id: " + id);
        
        Curador curadorEncontrado = getCurador(id);
        repository.delete(curadorEncontrado);
        
        return ResponseEntity.noContent().build();
    }
    
    private Curador getCurador(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Curador não encontrado"));
    }
    
}
