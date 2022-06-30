package com.example.wisley.academia.api.repository;

import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.repository.treino.TreinoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Long>, TreinoRepositoryQuery {

}
