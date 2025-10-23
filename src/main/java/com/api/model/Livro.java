package com.api.model;

import java.util.UUID;

// Livro.java
public class Livro {
    private final UUID id = UUID.randomUUID();
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(){}

    public Livro(String titulo, String autor, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public UUID getId() {
        return id;
    }

    
}
