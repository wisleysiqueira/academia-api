package com.example.wisley.academia.api.repository.permissao;

import com.example.wisley.academia.api.model.Permissao;
import com.example.wisley.academia.api.model.Permissao_;
import com.example.wisley.academia.api.repository.filter.PermissaoFilter;
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

public class PermissaoRepositoryImpl implements PermissaoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Permissao> filtrar(PermissaoFilter permissaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Permissao> criteriaQuery = builder.createQuery(Permissao.class);
        Root<Permissao> root = criteriaQuery.from(Permissao.class);
        Predicate[] predicates = criarRestricoes(permissaoFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Permissao> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacaoQuery(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(permissaoFilter));
    }

    private Predicate[] criarRestricoes(PermissaoFilter permissaoFilter, CriteriaBuilder builder, Root<Permissao> root){
        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(permissaoFilter.getDescricao())){
            predicates.add(builder.like(builder.lower(root.get(Permissao_.descricao)), "%" + permissaoFilter.getDescricao().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacaoQuery(TypedQuery<Permissao> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(PermissaoFilter permissaoFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Permissao> root = criteriaQuery.from(Permissao.class);
        Predicate[] predicates = criarRestricoes(permissaoFilter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
