package br.com.fiap.namastreta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.namastreta.exception.RestNotFoundException;
import br.com.fiap.namastreta.models.Curador;
import br.com.fiap.namastreta.repository.CuradorRepository;

@Service
public class CuradorService {

    @Autowired
    CuradorRepository curadorRepository;

    public Page<Curador> getAll(String nome, Pageable pageable) {
        return (nome == null) ? curadorRepository.findAll(pageable) : curadorRepository.findByNome(nome, pageable);
    }

    public Curador getById(Long id) {
        return curadorRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Curador não encontrado"));
    }

    public Curador save(Curador curador) {
        return curadorRepository.save(curador);
    }

    public Curador update(long id, Curador curador) {
        Curador curadorEncontrado = getById(id);

        curadorEncontrado.setFoto(curador.getFoto());
        curadorEncontrado.setNome(curador.getNome());
        curadorEncontrado.setDescricao(curador.getDescricao());

        return curadorRepository.save(curadorEncontrado);
    }

    public void delete(Long id) {
        curadorRepository.delete(getById(id));
    }

}
