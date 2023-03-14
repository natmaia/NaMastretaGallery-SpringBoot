package br.com.fiap.NaMastreta.models;

import com.fasterxml.jackson.databind.cfg.EnumFeature;

public class CadastroArtista {
    private Integer idArtista; 
    //private Integer idCurador; ------------- // extends da classe CadastroCurador
    private String nome;
    private Categoria categoria;
    private String descricao;




// Esta errado esse id, precisa ser de uma forma que ele mesmo crie o id, e some com osoutros curadores cadastrados - Maia
    public CadastroArtista(Integer idArtista, String nome, String string, String descricao) {
        this.idArtista = idArtista;
        this.nome = nome;
        this.descricao = descricao;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }


    


    
}

/*
 * 
* CADASTRO ARTISTA

NOME
CATEGORIA
DESCRIÇÃO

OBRAS
 */