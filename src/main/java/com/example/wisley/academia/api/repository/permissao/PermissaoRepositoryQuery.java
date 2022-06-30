package com.example.wisley.academia.api.repository.permissao;

import com.example.wisley.academia.api.model.Permissao;
import com.example.wisley.academia.api.repository.filter.PermissaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissaoRepositoryQuery {

    public Page<Permissao> filtrar(PermissaoFilter permissaoFilter, Pageable pageable);
}
