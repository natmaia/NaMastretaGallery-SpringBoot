package br.com.fiap.namastreta.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Categoria;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.models.Login;
import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;
import br.com.fiap.namastreta.repository.LoginRepository;
import br.com.fiap.namastreta.repository.ObraRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private CuradorRepository curadorRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    ObraRepository obraRepository;

    @Override
    public void run(String... args) throws Exception {
        Curador curador = new Curador("caminho/para/foto.jpg", "Nome do Curador", "Descrição do Curador",
                Categoria.FOFINHO, "2 anos");
        Artista artista = new Artista("url_foto", "Artista 1", "Descrição do Artista 1", Categoria.FOFINHO, null,
                curador);

        Login login1 = new Login();
        login1.setEmail("usuario1@example.com");
        login1.setSenha("MinhaSenha@123");

        Login login2 = new Login();
        login2.setEmail("usuario2@example.com");
        login2.setSenha("MinhaSenha@123");

        // Salvar registros
        curadorRepository.save(curador);
        artistaRepository.save(artista);
        loginRepository.save(login1);
        loginRepository.save(login2);

        obraRepository.saveAll(
                List.of(
                        new Obra("Foto1", "Jaelson", "descrição", curador, artista, new BigDecimal(400)),
                        new Obra("Foto2", "Nath", "descrição", curador, artista, new BigDecimal(10000))));

    }

}
