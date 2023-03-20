package br.com.fiap.NaMastreta.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Contatos {

    private String email;
    private String senha;
    private String redeSocial; // url da rede social
    private List <Obra> obras;

    public Contatos (){

    }

    public Contatos(String email, String senha, String redeSocial, List<Obra> obras){
        this.email = email;
        this.senha = senha;
        this.redeSocial = redeSocial;
        this.obras = obras;
    }
    
    

}
