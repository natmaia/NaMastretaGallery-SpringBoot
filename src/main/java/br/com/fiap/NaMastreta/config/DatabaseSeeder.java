package br.com.fiap.namastreta.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Categoria;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.models.Login;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;
import br.com.fiap.namastreta.repository.LoginRepository;
import br.com.fiap.namastreta.repository.ObraRepository;

@Component
@Profile("dev")
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

                curadorRepository.saveAll(List.of(
                                Curador.builder()
                                                .nome("Curador 1")
                                                .descricao("Descrição 1")
                                                .foto("foto1.jpg")
                                                .categoria(Categoria.CAOS_ARTISTICO)
                                                .tempoAtuacao("1 ano")
                                                .build(),
                                Curador.builder()
                                                .nome("Curador 2")
                                                .descricao("Descrição 2")
                                                .foto("foto2.jpg")
                                                .categoria(Categoria.CORES_VIBRANTES)
                                                .tempoAtuacao("2 ano")
                                                .build()));

                artistaRepository.saveAll(List.of(
                                Artista.builder()
                                                .nome("Artista 01")
                                                .foto("foto03.jpg")
                                                .categoria(Categoria.CAOS_ARTISTICO)
                                                .curador(curadorRepository.findById(1l).get())
                                                .build(),
                                Artista.builder()
                                                .nome("Artista 02")
                                                .foto("foto04.jpg")
                                                .categoria(Categoria.CORES_VIBRANTES)
                                                .curador(curadorRepository.findById(2l).get())
                                                .build()));

                Login login1 = new Login();
                login1.setEmail("usuario1@example.com");
                login1.setSenha("MinhaSenha@123");

                Login login2 = new Login();
                login2.setEmail("usuario2@example.com");
                login2.setSenha("MinhaSenha@123");

                // Salvar registros
                loginRepository.save(login1);
                loginRepository.save(login2);

                // obraRepository.saveAll(
                //                 List.of(
                //         Artista.builder().nome("Master of puppets").

                //                 ));

        }

}
