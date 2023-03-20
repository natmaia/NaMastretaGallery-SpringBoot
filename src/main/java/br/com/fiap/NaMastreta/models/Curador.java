package br.com.fiap.NaMastreta.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Curador extends DadosBase {
    private Integer id;
    private Categoria categoria;
    private String tempoAtuacao;
    private Contatos contatos;


	public Curador(){

    }


    public Curador(String foto, String nome, String descricao, Categoria categoria, String tempoAtuacao) {
        super(foto, nome, descricao);
        this.categoria = categoria;
        this.tempoAtuacao = tempoAtuacao;
        //this.contatos = contatos; --- comentei para verificar erro, tirei do parametro
    }


    public int size() {
        return 0;
    }


    public void add(Curador curador) {
    }
   
}