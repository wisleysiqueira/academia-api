package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.event.RecursoCriadoEvent;
import com.example.wisley.academia.api.model.Usuario;
import com.example.wisley.academia.api.repository.UsuarioRepository;
import com.example.wisley.academia.api.repository.filter.UsuarioFilter;
import com.example.wisley.academia.api.security.util.GeradorSenha;
import com.example.wisley.academia.api.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TREINO_SEARCH') and #oauth2.hasScope('read')")
    public Page<Usuario> pesquisar(UsuarioFilter usuarioFilter, Pageable pageable){
        return usuarioRepository.filtrar(usuarioFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TREINO_INSERT') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
        usuario.setDataHoraRegistro(LocalDateTime.now());
        String senhaCriptografada = GeradorSenha.gerarSenha(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));
        usuario.setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TREINO_VIEW') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<Usuario>> buscarPeloId(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent())
            return ResponseEntity.ok(usuario);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_TREINO_REMOVE') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TREINO_UPDATE') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
        Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
}