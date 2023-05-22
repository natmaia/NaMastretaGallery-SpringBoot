package br.com.fiap.namastreta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.repository.ObraRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/obra")
public class ObraController {

    private final Logger log = LoggerFactory.getLogger(ObraController.class);

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private PagedResourcesAssembler<Obra> assembler;

    

    private Obra getObra(Long id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Obra não encontrada"));
    }

    @GetMapping
    public PagedModel<EntityModel<Obra>> index(@RequestParam(required = false) String descricao, @PageableDefault(size = 5) Pageable pageable) {
        Page<Obra> obras = (descricao == null) ?
                obraRepository.findAll(pageable) :
                obraRepository.findByDescricaoContaining(descricao, pageable);
    
        return assembler.toModel(obras, Obra::toEntityModel);
    }
    

    @PostMapping
    public ResponseEntity<EntityModel<Obra>> cadastrarObra(@RequestBody @Valid Obra obra) {
        log.info("Cadastrando a obra: " + obra);
        Obra savedObra = obraRepository.save(obra);

        return ResponseEntity
                .created(savedObra.toEntityModel().getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(savedObra.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Obra> retornaObraComId(@PathVariable Long id) {
        log.info("Buscando obra por id: " + id);
        Obra obra = getObra(id);
        return obra.toEntityModel();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Obra>> update(@PathVariable Long id, @RequestBody @Valid Obra obra) {
        log.info("Atualizando obra com id: " + id);

        Obra obraEncontrada = getObra(id);

        obraEncontrada.setDescricao(obra.getDescricao());
        obraEncontrada.setFoto(obra.getFoto());
        obraEncontrada.setArtista(obra.getArtista());
        obraEncontrada.setCurador(obra.getCurador());
        obraEncontrada.setValor(obra.getValor());

        Obra updatedObra = obraRepository.save(obraEncontrada);

        return ResponseEntity.ok(updatedObra.toEntityModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletaObraComId(@PathVariable Long id) {
        log.info("Apagando obra através do id: " + id);

        Obra obraEncontrada = getObra(id);
        obraRepository.delete(obraEncontrada);

        return ResponseEntity.noContent().build();
    }

    public Class<?> show(Long id) {
        return null;
    }

    public Class<?> destroy(Long id) {
        return null;
    }
}
