package br.com.fiap.namastreta.DTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.controller.ObraController;
import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Categoria;
import br.com.fiap.namastreta.models.Curador;

@Relation(collectionRelation = "artistas")
public record ArtistaDTO(Long id, String nome, String foto, Categoria categoria,
        @JsonIgnore Curador curador) {

    public ArtistaDTO(Artista a) {
        this(a.getId(), a.getNome(), a.getFoto(), a.getCategoria(), a.getCurador());
    }

    public static EntityModel<ArtistaDTO> toEntityModel(Artista artista) {
        return EntityModel.of(
                new ArtistaDTO(artista),
                linkTo(methodOn(ArtistaController.class).show(artista.getId())).withSelfRel(),
                linkTo(methodOn(ArtistaController.class).deleteById(artista.getId())).withRel("delete"),
                linkTo(methodOn(ArtistaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(CuradorController.class).show(artista.getCurador().getId())).withRel("curador"),
                linkTo(methodOn(ObraController.class).getByIdArtista(artista.getId(), Pageable.unpaged()))
                        .withRel("obras"));

    }

    public EntityModel<ArtistaDTO> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ArtistaController.class).show(id())).withSelfRel(),
                linkTo(methodOn(ArtistaController.class).deleteById(id())).withRel("delete"),
                linkTo(methodOn(ArtistaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(CuradorController.class).show(curador().getId())).withRel("curador"),
                linkTo(methodOn(ObraController.class).getByIdArtista(id(), Pageable.unpaged()))
                        .withRel("obras"));

    }
}
