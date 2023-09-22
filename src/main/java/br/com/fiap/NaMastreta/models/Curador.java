package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.namastreta.controller.CuradorController;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class Curador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 100)
    private String descricao;

    @NotBlank(message = "A foto é obrigatória")
    private String foto;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotEmpty(message = "O tempo de atuação não pode estar vazio")
    private String tempoAtuacao;

    public EntityModel<Curador> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(CuradorController.class).show(getId())).withSelfRel(),
                linkTo(methodOn(CuradorController.class).destroy(getId())).withRel("delete"),
                linkTo(methodOn(CuradorController.class).index(null, Pageable.unpaged())).withRel("all"));
    }

}
