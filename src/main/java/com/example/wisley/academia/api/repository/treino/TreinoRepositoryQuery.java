package com.example.wisley.academia.api.repository.treino;


import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.repository.filter.TreinoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TreinoRepositoryQuery {

    public Page<Treino> filtrar(TreinoFilter treinoFilter, Pageable pageable);
}
