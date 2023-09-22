package br.com.fiap.namastreta.controller;

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

import br.com.fiap.namastreta.DTO.CuradorDTO;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.service.CuradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/curador")
public class CuradorController {

    @Autowired
    CuradorService curadorService;

    @Autowired
    PagedResourcesAssembler<CuradorDTO> assembler;

    @GetMapping
    public PagedModel<EntityModel<CuradorDTO>> index(@RequestParam(required = false) String nome,
            @PageableDefault() Pageable pageable) {
        Page<CuradorDTO> curadores = curadorService.getAll(nome, pageable).map(CuradorDTO::new);

        return assembler.toModel(curadores, entity -> entity.toEntityModel());
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "despesa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos, a validação falhou")
    })
    public ResponseEntity<EntityModel<Curador>> create(@RequestBody @Valid Curador curador) {
        log.info("Cadastrando curador: " + curador);
        Curador savedCurador = curadorService.save(curador);

        return ResponseEntity
                .created(savedCurador.toEntityModel().getRequiredLink("self").toUri())
                .body(savedCurador.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhes da despesa", description = "Retorna os dados de uma despesa passada pelo parâmetro de path id")
    public EntityModel<CuradorDTO> show(@PathVariable Long id) {
        log.info("Buscando curador por id: " + id);

        Curador curador = curadorService.getById(id);

        return CuradorDTO.toEntityModel(curador);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<CuradorDTO>> update(@PathVariable Long id, @RequestBody @Valid Curador curador) {
        log.info("Atualizando curador com id: " + id);

        return ResponseEntity.ok(CuradorDTO.toEntityModel(curadorService.update(id, curador)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        log.info("Apagando curador com id: " + id);

        curadorService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
