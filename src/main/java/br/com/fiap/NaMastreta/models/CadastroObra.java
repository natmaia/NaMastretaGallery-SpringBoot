package br.com.fiap.NaMastreta.models;

import java.math.BigDecimal;


public class CadastroObra {
    //private integer idCurador; ------------- // extends da classe CadastroCurador
    private byte[] ImgObra;
    private String Titulo;
    private String tamanho;
    private Integer idArtista;
    private String descricao;
    private BigDecimal valor;


    
    public CadastroObra(String titulo, String tamanho, String descricao) {
        Titulo = titulo;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }
    public byte[] getImgObra() {
        return ImgObra;
    }
    public void setImgObra(byte[] imgObra) {
        ImgObra = imgObra;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public Integer getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    // em caso de promoção!
    @Override
    public String toString() {
        return "Obra [valor=" + valor + ", descricao=" + descricao + "]";
    }

    
}

