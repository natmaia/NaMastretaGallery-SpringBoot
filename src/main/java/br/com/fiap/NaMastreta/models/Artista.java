package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.controller.ObraController;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Artista extends DadosBase {

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany
    private List<Obra> obras;

    @OneToOne
    @NotNull(message = "Curador não deve ser nulo")
    private Curador curador;

    public Artista(String foto, String nome, String descricao, Categoria categoria, List<Obra> obras, Curador curador) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.obras = obras;
        this.curador = curador;
    }

    public Artista(Artista a, Curador curador, List<Obra> obras) {
        super(a.getFoto(), a.getNome(), a.getDescricao());
        this.categoria = a.getCategoria();
        this.curador = curador;
        this.obras = obras;
    }

    public EntityModel<Artista> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ArtistaController.class).show(getId())).withSelfRel(),
                linkTo(methodOn(ArtistaController.class).deleteById(getId())).withRel("delete"),
                linkTo(methodOn(ArtistaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(CuradorController.class).show(getCurador().getId())).withRel("curador"),
                linkTo(methodOn(ObraController.class).index(null, Pageable.unpaged())).withRel("obras"));
    }

}
