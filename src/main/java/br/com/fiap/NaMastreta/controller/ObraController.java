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

import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.service.ObraService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/obra")
public class ObraController {

    @Autowired
    ObraService obraService;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String descricao,
            @PageableDefault(size = 5) Pageable pageable) {

        Page<Obra> obras = obraService.getObras(descricao, pageable);

        return assembler.toModel(obras.map(Obra::toEntityModel));

    }

    @GetMapping("artista/{id}")
    public PagedModel<EntityModel<Object>> getByIdArtista(@PathVariable Long id,
            @PageableDefault(size = 5) Pageable pageable) {

        Page<Obra> obras = obraService.getbyIdArtista(id, pageable);

        return assembler.toModel(obras.map(Obra::toEntityModel));

    }

    @PostMapping
    public ResponseEntity<EntityModel<Obra>> cadastrarObra(@RequestBody @Valid Obra obra) {
        log.info("Cadastrando a obra: " + obra);
        Obra savedObra = obraService.save(obra);

        return ResponseEntity
                .created(savedObra.toEntityModel().getRequiredLink("self").toUri())
                .body(savedObra.toEntityModel());
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Obra>> retornaObraComId(@PathVariable Long id) {
        log.info("Buscando obra por id: " + id);

        return ResponseEntity.ok(obraService.getObra(id).toEntityModel());
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Obra>> update(@PathVariable Long id, @RequestBody @Valid Obra obra) {
        log.info("Atualizando obra com id: " + id);

        return ResponseEntity.ok(obraService.update(obra, id).toEntityModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletaObraComId(@PathVariable Long id) {
        log.info("Apagando obra através do id: " + id);

        obraService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
