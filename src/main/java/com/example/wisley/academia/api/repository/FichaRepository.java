package com.example.wisley.academia.api.repository;

import com.example.wisley.academia.api.model.Ficha;
import com.example.wisley.academia.api.repository.ficha.FichaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichaRepository extends JpaRepository<Ficha, Long>, FichaRepositoryQuery {

}
