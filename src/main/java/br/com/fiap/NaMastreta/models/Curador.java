package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.controller.ObraController;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
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
@EqualsAndHashCode(callSuper=true)
public class Curador extends DadosBase {
   
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotEmpty(message = "O tempo de atuação não pode estar vazio")
    private String tempoAtuacao;

    @OneToOne
    private Contatos contatos;

    public Curador(String foto, String nome, String descricao, Categoria categoria, String tempoAtuacao) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.tempoAtuacao = tempoAtuacao;
    }

    public EntityModel<Curador> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(CuradorController.class).show(id)).withSelfRel(),
            linkTo(methodOn(CuradorController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(CuradorController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ArtistaController.class).show(this.getArtista().getId())).withRel("artista"),
            linkTo(methodOn(ObraController.class).show(this.getObra().getId())).withRel("obra")
        );
    }

    private Obra getObra() {
        return null;
    }

    private Artista getArtista() {
        return null;
    }
   
}


//@EqualsAndHashCode(callSuper=true) é para o @Data criar da super classe.