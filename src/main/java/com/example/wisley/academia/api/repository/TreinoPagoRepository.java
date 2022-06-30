package com.example.wisley.academia.api.repository;

import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.repository.treinopago.TreinoPagoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoPagoRepository extends JpaRepository<TreinoPago, Long>, TreinoPagoRepositoryQuery {
}
