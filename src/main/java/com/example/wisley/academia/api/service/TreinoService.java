package com.example.wisley.academia.api.service;

import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.repository.TreinoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    TreinoRepository treinoRepository;

    public Treino atualizar(Long id, Treino treino){
        Optional<Treino> treinoSalvo = treinoRepository.findById(id);
        if(!treinoSalvo.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(treino, treinoSalvo.get(), "id", "dataHoraRegistro");
        return treinoRepository.save(treinoSalvo.get());
    }
}
