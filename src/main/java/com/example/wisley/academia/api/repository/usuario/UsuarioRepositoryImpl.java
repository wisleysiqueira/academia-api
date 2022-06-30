package com.example.wisley.academia.api.repository.usuario;

import com.example.wisley.academia.api.model.Usuario;
import com.example.wisley.academia.api.model.Usuario_;
import com.example.wisley.academia.api.repository.filter.UsuarioFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacaoQuery(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
    }

    private Predicate[] criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder, Root<Usuario> root){
        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(usuarioFilter.getNome())){
            predicates.add(builder.like(builder.lower(root.get(Usuario_.nome)), "%" + usuarioFilter.getNome().toLowerCase() + "%"));
        }
        if(!ObjectUtils.isEmpty(usuarioFilter.getEmail())){
            predicates.add(builder.like(builder.lower(root.get(Usuario_.email)), "%" + usuarioFilter.getEmail().toLowerCase() + "%"));
        }
        if(!ObjectUtils.isEmpty(usuarioFilter.getTelefone())){
            predicates.add(builder.like(builder.lower(root.get(Usuario_.telefone)), "%" + usuarioFilter.getTelefone().toLowerCase() + "%"));
        }
        if(!ObjectUtils.isEmpty(usuarioFilter.getUserName())){
            predicates.add(builder.like(builder.lower(root.get(Usuario_.userName)), "%" + usuarioFilter.getUserName().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacaoQuery(TypedQuery<Usuario> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(UsuarioFilter usuarioFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public Optional<Usuario> findByUserName(String userName){
        TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.userName = :userName", Usuario.class);
        query.setParameter("userName", userName);
        return Optional.ofNullable(query.getSingleResult());
    }
}
