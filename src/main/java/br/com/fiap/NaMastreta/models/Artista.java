package br.com.fiap.namastreta.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.namastreta.controller.ArtistaController;
import br.com.fiap.namastreta.controller.CuradorController;
import br.com.fiap.namastreta.controller.ObraController;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Artista extends DadosBase {
    //COMENTEI ESSE ID PARA VERIFICAR O ERRO DA ENUM
        // @Id
        // @GeneratedValue(strategy = GenerationType.IDENTITY)
        // private Long id;

    
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany
    private List<Obra> obras;

    @OneToOne
    private Curador curador;

    public Artista(String foto, String nome, String descricao, Categoria categoria, List<Obra> obras, Curador curador) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.obras = obras;
        this.curador = curador;
        setId(getId() + 1);

    }

    public EntityModel<Artista> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ArtistaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ArtistaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ArtistaController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(CuradorController.class).show(this.getCurador().getId())).withRel("artista"),
            linkTo(methodOn(ObraController.class).show(this.getObra().getId())).withRel("obra")
        );
    }

    // Para fazer o link (em analise na regra de negocio)
    private DadosBase getObra() {
        return null;
    }
}

//@EqualsAndHashCode(callSuper=true) é para o @Data criar da super classe.

    
// Outra forma de fazer a incrementação é utilizando o prepersist - verificar com o prof qual a melhor forma
    // @PrePersist
    // public void incrementId() {
    //     if (getId() == null) {
    //         setId(0L);
    //     }
    //     setId(getId() + 1);
    // }

