package com.example.wisley.academia.api.service;

import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.repository.TreinoPagoRepository;
import com.example.wisley.academia.api.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TreinoPagoService {

    @Autowired
    TreinoPagoRepository treinoPagoRepository;

    public void cancelar(Long id) {
        TreinoPago treinoPagoSalvo = buscarTreinoPagoPeloId(id);
        treinoPagoSalvo.setCancelado(true);
        treinoPagoRepository.save(treinoPagoSalvo);
    }

    private TreinoPago buscarTreinoPagoPeloId(Long id) {
        TreinoPago treinoPagoSalvo = this.treinoPagoRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return treinoPagoSalvo;
    }

}
