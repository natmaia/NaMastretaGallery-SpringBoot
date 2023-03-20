package br.com.fiap.NaMastreta.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Obra extends DadosBase {
    
    private Integer id;
    private String tamanho;
    private Curador curador;
    private Artista artista;
    private BigDecimal valor;


    
    public Obra() {
    }

  
    public Obra(String foto, String nome, String descricao, String tamanho, Curador curador, Artista artista, BigDecimal valor) {
        super(foto, nome, descricao);
        this.tamanho = tamanho;
        this.curador = curador;
        this.artista = artista;
        this.valor = valor;
    }


    // em caso de promoção!
    @Override
    public String toString() {
        return "Obra [valor=" + valor + ", descricao=" + super.getDescricao() + "]";
    }


    public int size() {
        return 0;
    }

    
}

