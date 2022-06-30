package com.example.wisley.academia.api.repository.treinopago;

import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.model.TreinoPago_;
import com.example.wisley.academia.api.repository.filter.TreinoPagoFilter;
import org.apache.commons.lang3.time.DateUtils;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TreinoPagoRepositoryImpl implements TreinoPagoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<TreinoPago> filtrar(TreinoPagoFilter treinoPagoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TreinoPago> criteriaQuery = builder.createQuery(TreinoPago.class);
        Root<TreinoPago> root = criteriaQuery.from(TreinoPago.class);
        Predicate[] predicates = criarRestricoes(treinoPagoFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<TreinoPago> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacaoQuery(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(treinoPagoFilter));
    }

    private Predicate[] criarRestricoes(TreinoPagoFilter treinoPagoFilter, CriteriaBuilder builder, Root<TreinoPago> root){
        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(treinoPagoFilter.getId())){
            predicates.add(builder.equal(root.get(TreinoPago_.id), treinoPagoFilter.getId()));
        }
        if(!ObjectUtils.isEmpty(treinoPagoFilter.getNome())){
            predicates.add(
                    builder.like(builder.lower(root.get(TreinoPago_.usuario.getName())), "%" + treinoPagoFilter.getNome().toLowerCase() + "%")
            );
        }
        if(!ObjectUtils.isEmpty(treinoPagoFilter.getDataDe())){
            LocalDateTime dataHoraDeFormatada = treinoPagoFilter.getDataDe().atTime(LocalTime.MIN);
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(TreinoPago_.dataHoraRegistro), dataHoraDeFormatada)
            );
        }
        if(!ObjectUtils.isEmpty(treinoPagoFilter.getDataAte())){
            LocalDateTime dataHoraAteFormatada = treinoPagoFilter.getDataAte().atTime(LocalTime.MAX);
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(TreinoPago_.dataHoraRegistro), dataHoraAteFormatada)
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacaoQuery(TypedQuery<TreinoPago> query, Pageable pageable){
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(TreinoPagoFilter treinoPagoFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<TreinoPago> root = criteriaQuery.from(TreinoPago.class);
        Predicate[] predicates = criarRestricoes(treinoPagoFilter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<TreinoPago> listarPorMesAno(int mes, int ano) throws Exception {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, ano);
        instance.set(Calendar.MONTH, mes - 1);
        int ultimoDiaMes = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        LocalDateTime dataHoraDe = LocalDateTime.of(ano,mes,1, LocalTime.MIN.getHour(),LocalTime.MIN.getMinute(), LocalTime.MIN.getSecond());
        LocalDateTime dataHoraAte = LocalDateTime.of(ano,mes,ultimoDiaMes, LocalTime.MAX.getHour(),LocalTime.MAX.getMinute(), LocalTime.MAX.getSecond());
        try {
            TypedQuery<TreinoPago> query = manager.createQuery(
                    "SELECT t FROM TreinoPago t WHERE t.dataHoraRegistro >= :dataHoraDe AND t.dataHoraRegistro <= :dataHoraAte ORDER BY t.dataHoraRegistro", TreinoPago.class
            );
            query.setParameter("dataHoraDe", dataHoraDe);
            query.setParameter("dataHoraAte", dataHoraAte);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
