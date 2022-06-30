package com.example.wisley.academia.api.repository;

import com.example.wisley.academia.api.model.Permissao;
import com.example.wisley.academia.api.repository.permissao.PermissaoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {
}
