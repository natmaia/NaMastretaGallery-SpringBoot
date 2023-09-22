package br.com.fiap.namastreta.DTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.models.Categoria;
import br.com.fiap.namastreta.models.Curador;

@Relation(collectionRelation = "curadores")
public record CuradorDTO(Long id, String nome, String descricao, String foto, Categoria categoria,
        String tempoAtuacao) {

    public CuradorDTO(Curador c) {
        this(c.getId(), c.getNome(), c.getDescricao(), c.getFoto(), c.getCategoria(), c.getTempoAtuacao());
    }

    public static EntityModel<CuradorDTO> toEntityModel(Curador curador) {
        return EntityModel.of(
                new CuradorDTO(curador),
                linkTo(methodOn(CuradorController.class).show(curador.getId())).withSelfRel(),
                linkTo(methodOn(CuradorController.class).destroy(curador.getId())).withRel("delete"),
                linkTo(methodOn(CuradorController.class).index(null, Pageable.unpaged())).withRel("all"));

    }

    public EntityModel<CuradorDTO> toEntityModel() {
        return EntityModel.of(
               this,
                linkTo(methodOn(CuradorController.class).show(id())).withSelfRel(),
                linkTo(methodOn(CuradorController.class).destroy(id())).withRel("delete"),
                linkTo(methodOn(CuradorController.class).index(null, Pageable.unpaged())).withRel("all"));

    }

}
