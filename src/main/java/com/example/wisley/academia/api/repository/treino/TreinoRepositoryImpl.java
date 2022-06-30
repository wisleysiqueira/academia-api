package com.example.wisley.academia.api.repository.treino;

import com.example.wisley.academia.api.model.Treino;
import com.example.wisley.academia.api.model.Treino_;
import com.example.wisley.academia.api.repository.filter.TreinoFilter;
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

public class TreinoRepositoryImpl implements TreinoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Treino> filtrar(TreinoFilter treinoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Treino> criteriaQuery = builder.createQuery(Treino.class);
        Root<Treino> root = criteriaQuery.from(Treino.class);
        Predicate[] predicates = criarRestricoes(treinoFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Treino> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacaoQuery(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(treinoFilter));
    }

    private Predicate[] criarRestricoes(TreinoFilter treinoFilter, CriteriaBuilder builder, Root<Treino> root){
        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(treinoFilter.getDescricao())){
            predicates.add(builder.like(builder.lower(root.get(Treino_.descricao)), "%" + treinoFilter.getDescricao().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacaoQuery(TypedQuery<Treino> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(TreinoFilter treinoFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Treino> root = criteriaQuery.from(Treino.class);
        Predicate[] predicates = criarRestricoes(treinoFilter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
