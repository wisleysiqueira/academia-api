package com.example.wisley.academia.api.service;

import com.example.wisley.academia.api.model.Ficha;
import com.example.wisley.academia.api.repository.FichaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FichaService {

    @Autowired
    FichaRepository fichaRepository;

    public Ficha atualizar(Long id, Ficha ficha){
        Optional<Ficha> fichaSalva = fichaRepository.findById(id);
        if(!fichaSalva.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(ficha, fichaSalva.get(), "id", "dataHoraRegistro");
        return fichaRepository.save(fichaSalva.get());
    }
}
