package com.api.model;

// Emprestimo.java
import java.time.LocalDateTime;
import java.util.UUID;

public class Emprestimo {
    private final UUID id = UUID.randomUUID();
    private Usuario usuario;
    private Livro livro;
    private LocalDateTime dataEmprestimo;

    public Emprestimo(){}

    public Emprestimo(Usuario usuario, Livro livro, LocalDateTime dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public UUID getId() {
        return id;
    }

    
}

