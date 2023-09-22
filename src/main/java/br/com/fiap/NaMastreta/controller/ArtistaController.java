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

import br.com.fiap.namastreta.DTO.ArtistaDTO;
import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/artista")
@SecurityRequirement(name = "bearer-key")
public class ArtistaController {

    Logger log = LoggerFactory.getLogger(ArtistaController.class);

    @Autowired
    ArtistaService artistaService;

    @Autowired
    PagedResourcesAssembler<ArtistaDTO> assembler;

    @GetMapping
    public PagedModel<EntityModel<ArtistaDTO>> index(@RequestParam(required = false) String nome,
            @PageableDefault() Pageable pageable) {
        Page<ArtistaDTO> artistas = artistaService.GetAll(nome, pageable).map(ArtistaDTO::new);

        return assembler.toModel(artistas, entity -> entity.toEntityModel());
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "despesa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "dados inválidos, a validação falhou")
    })
    public ResponseEntity<EntityModel<Artista>> cadastrar(@RequestBody @Valid Artista artista) {
        log.info("Cadastrando novo artista: " + artista);
        Artista savedArtista = artistaService.save(artista);

        return ResponseEntity
                .created(savedArtista.toEntityModel().getRequiredLink("self").toUri())
                .body(savedArtista.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhes de um artista", description = "Retorna os dados de um artista passada pelo parâmetro de path id")
    public EntityModel<ArtistaDTO> show(@PathVariable Long id) {

        log.info("Buscando artista por id: " + id);

        Artista artista = artistaService.getArtista(id);

        return ArtistaDTO.toEntityModel(artista);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<ArtistaDTO>> updateById(@PathVariable Long id,
            @RequestBody @Valid Artista artista) {
        log.info("Atualizando artista com id: " + id);

        return ResponseEntity.ok(ArtistaDTO.toEntityModel(artistaService.update(id, artista)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Apagando artista com id: " + id);

        artistaService.delete(id);

        return ResponseEntity.noContent().build();
    }

}