package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.event.RecursoCriadoEvent;
import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.repository.TreinoPagoRepository;
import com.example.wisley.academia.api.repository.filter.TreinoPagoFilter;
import com.example.wisley.academia.api.service.TreinoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/treinos-pagos")
public class TreinoPagoResource {

    @Autowired
    TreinoPagoRepository treinoPagoRepository;

    @Autowired
    TreinoPagoService treinoPagoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TREINOPAGO_SEARCH') and #oauth2.hasScope('read')")
    public Page<TreinoPago> pesquisar(TreinoPagoFilter treinoPagoFilter, Pageable pageable){
        return treinoPagoRepository.filtrar(treinoPagoFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TREINOPAGO_INSERT') and #oauth2.hasScope('write')")
    public ResponseEntity<TreinoPago> criar(@Valid @RequestBody TreinoPago treinoPago, HttpServletResponse response) {
        treinoPago.setDataHoraRegistro(LocalDateTime.now());
        treinoPago.setCancelado(false);
        TreinoPago treinoPagoSalvo = treinoPagoRepository.save(treinoPago);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, treinoPagoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoPago);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TREINOPAGO_VIEW') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<TreinoPago>> buscarPeloId(@PathVariable Long id){
        Optional<TreinoPago> treinoPago = treinoPagoRepository.findById(id);
        if (treinoPago.isPresent())
            return ResponseEntity.ok(treinoPago);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_TREINOPAGO_REMOVE') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) {
        treinoPagoRepository.deleteById(id);
    }

    @PutMapping("/{id}/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_TREINOPAGO_UPDATE') and #oauth2.hasScope('write')")
    public void cancelarTreinoPago(@PathVariable Long id) {
        treinoPagoService.cancelar(id);
    }
}
