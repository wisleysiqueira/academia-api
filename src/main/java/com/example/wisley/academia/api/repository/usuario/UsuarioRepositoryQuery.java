package com.example.wisley.academia.api.repository.usuario;

import com.example.wisley.academia.api.model.Usuario;
import com.example.wisley.academia.api.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioRepositoryQuery {

    public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

    public Optional<Usuario> findByUserName(String userName);
}
