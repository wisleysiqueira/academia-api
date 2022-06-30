package com.example.wisley.academia.api.service;

import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.model.Usuario;
import com.example.wisley.academia.api.repository.TreinoRepository;
import com.example.wisley.academia.api.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario atualizar(Long id, Usuario usuario){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if(!usuarioSalvo.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(usuario, usuarioSalvo.get(), "id", "dataHoraRegistro");
        return usuarioRepository.save(usuarioSalvo.get());
    }
}
