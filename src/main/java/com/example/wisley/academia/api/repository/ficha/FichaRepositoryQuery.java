package com.example.wisley.academia.api.repository.ficha;

import com.example.wisley.academia.api.model.Ficha;
import com.example.wisley.academia.api.repository.filter.FichaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FichaRepositoryQuery {

    public Page<Ficha> filtrar(FichaFilter fichaFilter, Pageable pageable);
}
