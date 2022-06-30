package com.example.wisley.academia.api.service;

import com.example.wisley.academia.api.model.Permissao;
import com.example.wisley.academia.api.repository.PermissaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissaoService {

    @Autowired
    PermissaoRepository permissaoRepository;

    public Permissao atualizar(Long id, Permissao permissao){
        Optional<Permissao> permissaoSalva = permissaoRepository.findById(id);
        if(!permissaoSalva.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(permissao, permissaoSalva.get(), "id", "dataHoraRegistro");
        return permissaoRepository.save(permissaoSalva.get());
    }
}
