package br.com.fiap.namastreta.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Categoria;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;

public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    ArtistaRepository artistaRepository;

    @Autowired
    CuradorRepository curadorRepository;

    @Override
    public void run(String... args) throws Exception {
        Artista a1 = new Artista("url","Maia", "test",Categoria.ROMANTISMO, null, null);
        Artista a2 = new Artista("url","Maia", "test",Categoria.NERVOSINHO , null, null);
        Artista a3 = new Artista("url","Maia", "test",Categoria.RAIVOSO, null, null);
        artistaRepository.saveAll(List.of(a1, a2, a3));

        Curador c1 = new Curador("url", "nome", "test 2", Categoria.FOFINHO, "2 anos");
        Curador c2 = new Curador("url", "nome", "test 3", Categoria.NERVOSINHO, "3 anos");
        Curador c3 = new Curador("url", "nome", "test 3", Categoria.RAIVOSO, "4 anos");
        curadorRepository.saveAll(List.of(c1, c2, c3));
         
         
    }

}