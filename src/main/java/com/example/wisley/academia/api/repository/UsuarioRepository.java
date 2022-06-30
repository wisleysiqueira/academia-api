package com.example.wisley.academia.api.repository;

import com.example.wisley.academia.api.model.Usuario;
import com.example.wisley.academia.api.repository.usuario.UsuarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
}
