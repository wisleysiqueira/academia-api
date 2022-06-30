package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.event.RecursoCriadoEvent;
import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.repository.TreinoRepository;
import com.example.wisley.academia.api.repository.filter.TreinoFilter;
import com.example.wisley.academia.api.service.TreinoService;
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
@RequestMapping("/treinos")
public class TreinoResource {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TREINO_SEARCH') and #oauth2.hasScope('read')")
    public Page<Treino> pesquisar(TreinoFilter treinoFilter, Pageable pageable){
        return treinoRepository.filtrar(treinoFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TREINO_INSERT') and #oauth2.hasScope('write')")
    public ResponseEntity<Treino> criar(@Valid @RequestBody Treino treino, HttpServletResponse response) {
        treino.setDataHoraRegistro(LocalDateTime.now());
        Treino treinoSalvo = treinoRepository.save(treino);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, treinoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(treinoSalvo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TREINO_VIEW') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<Treino>> buscarPeloId(@PathVariable Long id){
        Optional<Treino> treino = treinoRepository.findById(id);
        if (treino.isPresent())
            return ResponseEntity.ok(treino);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_TREINO_REMOVE') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) {
        treinoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TREINO_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Treino> atualizar(@PathVariable Long id, @Valid @RequestBody Treino treino){
        Treino treinoSalvo = treinoService.atualizar(id, treino);
        return ResponseEntity.ok(treinoSalvo);
    }

}
