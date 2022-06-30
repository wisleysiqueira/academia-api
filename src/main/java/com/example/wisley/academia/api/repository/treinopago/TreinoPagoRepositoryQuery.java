package com.example.wisley.academia.api.repository.treinopago;

import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.repository.filter.TreinoPagoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreinoPagoRepositoryQuery {

    public Page<TreinoPago> filtrar(TreinoPagoFilter treinoPagoFilter, Pageable pageable);

    public List<TreinoPago> listarPorMesAno(int mes, int ano) throws Exception;
}
