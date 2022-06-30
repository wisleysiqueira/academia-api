package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.event.RecursoCriadoEvent;
import com.example.wisley.academia.api.model.Ficha;
import com.example.wisley.academia.api.repository.FichaRepository;
import com.example.wisley.academia.api.repository.filter.FichaFilter;
import com.example.wisley.academia.api.service.FichaService;
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
@RequestMapping("/fichas")
public class FichaResource {

    @Autowired
    FichaRepository fichaRepository;

    @Autowired
    FichaService fichaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_FICHA_SEARCH') and #oauth2.hasScope('read')")
    public Page<Ficha> pesquisar(FichaFilter fichaFilter, Pageable pageable){
        return fichaRepository.filtrar(fichaFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_FICHA_INSERT') and #oauth2.hasScope('write')")
    public ResponseEntity<Ficha> criar(@Valid @RequestBody Ficha ficha, HttpServletResponse response) {
        ficha.setDataHoraRegistro(LocalDateTime.now());
        Ficha fichaSalva = fichaRepository.save(ficha);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, fichaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaSalva);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FICHA_VIEW') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<Ficha>> buscarPeloId(@PathVariable Long id){
        Optional<Ficha> ficha = fichaRepository.findById(id);
        if (ficha.isPresent())
            return ResponseEntity.ok(ficha);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_FICHA_REMOVE') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) {
        fichaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FICHA_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Ficha> atualizar(@PathVariable Long id, @Valid @RequestBody Ficha ficha){
        Ficha fichaSalva = fichaService.atualizar(id, ficha);
        return ResponseEntity.ok(fichaSalva);
    }
}
