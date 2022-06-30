package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.event.RecursoCriadoEvent;
import com.example.wisley.academia.api.model.Permissao;
import com.example.wisley.academia.api.repository.PermissaoRepository;
import com.example.wisley.academia.api.repository.filter.PermissaoFilter;
import com.example.wisley.academia.api.service.PermissaoService;
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
@RequestMapping("/permissoes")
public class PermissaoResource {

    @Autowired
    PermissaoRepository permissaoRepository;

    @Autowired
    PermissaoService permissaoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PERMISSAO_SEARCH') and #oauth2.hasScope('read')")
    public Page<Permissao> pesquisar(PermissaoFilter permissaoFilter, Pageable pageable){
        return permissaoRepository.filtrar(permissaoFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PERMISSAO_INSERT') and #oauth2.hasScope('write')")
    public ResponseEntity<Permissao> criar(@Valid @RequestBody Permissao permissao, HttpServletResponse response) {
        permissao.setDataHoraRegistro(LocalDateTime.now());
        Permissao permissaoSalva = permissaoRepository.save(permissao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, permissaoSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(permissaoSalva);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PERMISSAO_VIEW') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<Permissao>> buscarPeloId(@PathVariable Long id){
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        if (permissao.isPresent())
            return ResponseEntity.ok(permissao);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_PERMISSAO_REMOVE') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) {
        permissaoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PERMISSAO_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Permissao> atualizar(@PathVariable Long id, @Valid @RequestBody Permissao permissao){
        Permissao permissaoSalva = permissaoService.atualizar(id, permissao);
        return ResponseEntity.ok(permissaoSalva);
    }
}
