package br.com.fiap.NaMastreta.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Artista extends DadosBase {

    private Integer id;
    private Categoria categoria;
    private List<Obra> obras;
    private Curador curador;

    public Artista() {
    }

    public Artista(String foto, String nome, String descricao, Categoria categoria, List<Obra> obras, Curador curador) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.obras = obras;
        this.curador = curador;

    }

}
