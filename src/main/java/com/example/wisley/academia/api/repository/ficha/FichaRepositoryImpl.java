package com.example.wisley.academia.api.repository.ficha;

import com.example.wisley.academia.api.model.Ficha;
import com.example.wisley.academia.api.model.Ficha_;
import com.example.wisley.academia.api.repository.filter.FichaFilter;
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

public class FichaRepositoryImpl implements FichaRepositoryQuery{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Ficha> filtrar(FichaFilter fichaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Ficha> criteriaQuery = builder.createQuery(Ficha.class);
        Root<Ficha> root = criteriaQuery.from(Ficha.class);
        Predicate[] predicates = criarRestricoes(fichaFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Ficha> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacaoQuery(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(fichaFilter));
    }

    private Predicate[] criarRestricoes(FichaFilter fichaFilter, CriteriaBuilder builder, Root<Ficha> root){
        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(fichaFilter.getId())){
            predicates.add(builder.equal(root.get(Ficha_.id), fichaFilter.getId()));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacaoQuery(TypedQuery<Ficha> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(FichaFilter fichaFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Ficha> root = criteriaQuery.from(Ficha.class);
        Predicate[] predicates = criarRestricoes(fichaFilter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
