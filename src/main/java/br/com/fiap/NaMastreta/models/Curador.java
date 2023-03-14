package br.com.fiap.NaMastreta.models;

public class Curador {
    
    private byte[] foto;
    private String nome;
    private Integer id;

    public Curador(String string, Login login2) {
    }

    public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

       
    
}
