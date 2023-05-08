package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
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
@EqualsAndHashCode(callSuper=true)
public class Obra extends DadosBase {
 

    @ManyToOne
    @JoinColumn(name = "curador_id")
    private Curador curador;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
    
    @Min(value = 0, message = "deve ser positivo") 
    @NotNull
    private BigDecimal valor;

    protected Obra() {
    }

    public Obra(String foto, String nome, String descricao, Curador curador, Artista artista,
            BigDecimal valor) {
        super(foto, nome, descricao);
        //this.tamanho = tamanho;
        this.curador = curador;
        this.artista = artista;
        this.valor = valor;
        setId(getId() + 1);
    }

    // em caso de promoção!
    @Override
    public String toString() {
        return "Obra [valor=" + valor + ", descricao=" + super.getDescricao() + "]";
    }

    public int size() {
        return 0;
    }

    public EntityModel<Obra> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ObraController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ObraController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ObraController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ArtistaController.class).show(this.getArtista().getId())).withRel("artista"),
            linkTo(methodOn(CuradorController.class).show(this.getArtista().getId())).withRel("curador")
        );
    }

    public static Object builder() {
        return null;
    }

}

//@EqualsAndHashCode(callSuper=true) é para o @Data criar da super classe.