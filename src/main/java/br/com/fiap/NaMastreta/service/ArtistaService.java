package br.com.fiap.namastreta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;

@Service
public class ArtistaService {

    @Autowired
    ArtistaRepository artistaRepository;

    @Autowired
    CuradorRepository curadorRepository;

    public Page<Artista> GetAll(String nome, Pageable pageable) {
        return (nome == null) ? artistaRepository.findAll(pageable) : artistaRepository.findByNome(nome, pageable);
    }

    public Artista save(Artista artista) {

        artista.setCurador(getCuradorByArtista(artista));

        return artistaRepository.save(artista);
    }

    public Artista update(Long id, Artista artista) {

        Artista artistaEncontrado = getArtista(id);

        artistaEncontrado.setFoto(artista.getFoto());
        artistaEncontrado.setNome(artista.getNome());
        artistaEncontrado.setCategoria(artista.getCategoria());

        return artistaRepository.save(artistaEncontrado);

    }

    public void delete(Long id) {
        Artista artistaEncontrado = getArtista(id);
        artistaRepository.delete(artistaEncontrado);
    }

    public Artista getArtista(Long id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrado"));
    }

    private Curador getCuradorByArtista(Artista artista) {
        return curadorRepository.findById(artista.getCurador().getId())
                .orElseThrow(() -> new RestNotFoundException("Curador não encontrada"));
    }

}
