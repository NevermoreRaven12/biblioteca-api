package com.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.model.Livro;
import com.api.repository.LivroRepository;

public class LivroService {
    private LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public Livro cadastrarLivro(String titulo, String autor){
        if((titulo == null || autor == null) || (titulo.isBlank() || autor.isBlank())){
            throw new IllegalArgumentException("O livro deve conter um título e uma descrição.");
        }

        Livro livro = new Livro(titulo,autor,true);
        repository.adicionar(livro);
        return livro;
    }

    public List<Livro> listarLivros(){
        return repository.listarTodos();
    }

    public Livro buscarLivro(UUID id){
        return repository.buscarPorId(id);
    }

    public Optional<Livro> buscarLivro(String titulo){
        List<Livro> livros = repository.listarTodos();
        for (Livro livro : livros){
            if(livro.getTitulo().equalsIgnoreCase(titulo)){
                return Optional.of(livro);
            }
        }

        return Optional.empty();


    }

    public boolean removerLivro(UUID id){
        Livro livro = repository.buscarPorId(id);
        if (livro != null){
            repository.remover(id);
            return true;
        }
        return false;
    }

   
}
