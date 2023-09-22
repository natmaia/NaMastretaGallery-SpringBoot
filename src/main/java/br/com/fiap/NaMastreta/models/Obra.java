package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.fiap.namastreta.controller.ObraController;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Relation(collectionRelation = "obras")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "A foto é obrigatória")
    private String foto;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 100)
    private String descricao;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "curador_id", nullable = false)
    @NotNull(message = "Curador não deve ser nulo")
    private Curador curador;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "artista_id", nullable = false)
    @NotNull(message = "artista não deve ser nulo")
    private Artista artista;

    @Min(value = 0, message = "Deve ser um valor positivo")
    @NotNull
    private BigDecimal valor;

    public EntityModel<Obra> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ObraController.class).retornaObraComId(getId())).withSelfRel(),
                linkTo(methodOn(ObraController.class).deletaObraComId(getId())).withRel("delete"),
                linkTo(methodOn(ObraController.class).index(null, Pageable.unpaged())).withRel("all"));
    }
}
