package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.controller.ObraController;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EqualsAndHashCode(of = "id")

@Relation(collectionRelation = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "A foto é obrigatória")
    private String foto;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Curador não deve ser nulo")
    private Curador curador;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "artista")
    private List<Obra> obras;

    public EntityModel<Artista> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ArtistaController.class).show(getId())).withSelfRel(),
                linkTo(methodOn(ArtistaController.class).deleteById(getId())).withRel("delete"),
                linkTo(methodOn(ArtistaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(CuradorController.class).show(getCurador().getId())).withRel("curador"),
                linkTo(methodOn(ObraController.class).getByIdArtista(getId(), Pageable.unpaged())).withRel("obras"));
    }

}
