package br.com.fiap.NaMastreta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Curador extends DadosBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Categoria categoria;
    private String tempoAtuacao;
    private Contatos contatos;


	protected Curador(){

    }


    public Curador(String foto, String nome, String descricao, Categoria categoria, String tempoAtuacao) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.tempoAtuacao = tempoAtuacao;
    }


    public int size() {
        return 0;
    }


    public void add(Curador curador) {
    }
   
}