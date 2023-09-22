package br.com.fiap.namastreta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Artista;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.models.Obra;
import br.com.fiap.namastreta.repository.ArtistaRepository;
import br.com.fiap.namastreta.repository.CuradorRepository;
import br.com.fiap.namastreta.repository.ObraRepository;

@Service
public class ObraService {

    @Autowired
    ObraRepository obraRepository;

    @Autowired
    CuradorRepository curadorRepository;

    @Autowired
    ArtistaRepository artistaRepository;

    public Page<Obra> getbyIdArtista(Long id, Pageable pageable) {
        return obraRepository.findByArtistaId(id, pageable);
    }

    public Page<Obra> getObras(String descricao, Pageable pageable) {

        return (descricao == null) ? obraRepository.findAll(pageable)
                : obraRepository.findByDescricaoContaining(descricao, pageable);
    }

    public Obra save(Obra obra) {

        obra.setArtista(getArtistaByObra(obra));
        obra.setCurador(getCuradorByObra(obra));

        return obraRepository.save(obra);

    }

    public Obra update(Obra obra, Long id) {
        Obra obraEncontrada = getObra(id);

        obraEncontrada.setDescricao(obra.getDescricao());
        obraEncontrada.setFoto(obra.getFoto());
        obraEncontrada.setCurador(obra.getCurador());
        obraEncontrada.setValor(obra.getValor());

        return obraRepository.save(obraEncontrada);
    }

    public Obra getObra(Long id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Obra não encontrada"));
    }

    public void delete(long id) {

        obraRepository.delete(getObra(id));

    }

    private Artista getArtistaByObra(Obra obra) {
        return artistaRepository.findById(obra.getArtista().getId())
                .orElseThrow(() -> new RestNotFoundException("Artista não encontrada"));
    }

    private Curador getCuradorByObra(Obra obra) {
        return curadorRepository.findById(obra.getCurador().getId())
                .orElseThrow(() -> new RestNotFoundException("Curador não encontrada"));
    }

}
