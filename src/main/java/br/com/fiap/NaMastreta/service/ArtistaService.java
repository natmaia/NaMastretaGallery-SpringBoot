package br.com.fiap.namastreta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;
import br.com.fiap.namastreta.repository.ObraRepository;

@Service
public class ArtistaService {

    @Autowired
    ArtistaRepository artistaRepository;

    @Autowired
    ObraRepository obraRepository;

    @Autowired
    CuradorRepository curadorRepository;

    @Autowired
    PagedResourcesAssembler<Artista> assembler;

    public Artista getArtistaWithCuradorAndArtista(long id, Pageable pageable) {

        Artista artista = getArtista(id);
        Curador curador = curadorRepository.findById(artista.getCurador().getId())
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrado"));

        Page<Obra> obras = obraRepository.findByArtistaId(artista.getId(), pageable);
        return new Artista(artista, curador, obras.getContent());
    }

    public Page<Artista> GetAll(String nome, Pageable pageable) {
        return (nome == null) ? artistaRepository.findAll(pageable) : artistaRepository.findByNome(nome, pageable);
    }

    public Artista save(Artista artista) {

        curadorRepository.save(artista.getCurador());

        return artistaRepository.save(artista);
    }

    public Artista update(Long id, Artista artista) {

        Artista artistaEncontrado = getArtista(id);

        artistaEncontrado.setFoto(artista.getFoto());
        artistaEncontrado.setNome(artista.getNome());
        artistaEncontrado.setDescricao(artista.getDescricao());
        artistaEncontrado.setCategoria(artista.getCategoria());
        artistaEncontrado.setCurador(artista.getCurador());

        return artistaRepository.save(artistaEncontrado);

    }

    public void delete(Long id) {
        Artista artistaEncontrado = getArtista(id);
        artistaRepository.delete(artistaEncontrado);
    }

    private Artista getArtista(Long id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrado"));
    }

}
