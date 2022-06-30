package com.example.wisley.academia.api.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TreinoPagoFilter {

    private Long id;

    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDe() {
        return dataDe;
    }

    public void setDataDe(LocalDate dataDe) {
        this.dataDe = dataDe;
    }

    public LocalDate getDataAte() {
        return dataAte;
    }

    public void setDataAte(LocalDate dataAte) {
        this.dataAte = dataAte;
    }
}
