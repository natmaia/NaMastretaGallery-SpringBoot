package br.com.fiap.namastreta.models;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.fiap.namastreta.controller.ObraController;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Obra extends DadosBase {

    @ManyToOne
    @NotNull(message = "Curador não deve ser nulo")
    @JoinColumn(name = "curador_id")
    private Curador curador;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    @NotNull(message = "artista não deve ser nulo")
    private Artista artista;

    @Min(value = 0, message = "Deve ser um valor positivo")
    @NotNull
    private BigDecimal valor;

    protected Obra() {
    }

    public Obra(String foto, String nome, String descricao, Curador curador, Artista artista,
            BigDecimal valor) {
        super(foto, nome, descricao);
        this.curador = curador;
        this.artista = artista;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Obra [valor=" + valor + ", descricao=" + super.getDescricao() + "]";
    }

    public EntityModel<Obra> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ObraController.class).retornaObraComId(id)).withSelfRel(),
                linkTo(methodOn(ObraController.class).deletaObraComId(id)).withRel("delete"),
                linkTo(methodOn(ObraController.class).index(null, Pageable.unpaged())).withRel("all"));
    }
}

// @EqualsAndHashCode(callSuper=true) é para o @Data criar da super classe.