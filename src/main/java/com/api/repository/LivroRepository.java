package com.api.repository;
import com.api.model.Livro;

// LivroRepository.java
import java.util.*;

public class LivroRepository {
    private List<Livro> livros = new ArrayList<>();

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarPorId(UUID id) {
        return livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId().equals(livroAtualizado.getId())) {
                livros.set(i, livroAtualizado);
                return;
            }
        }
    }

    public void remover(UUID id) {
        livros.removeIf(l -> l.getId().equals(id));
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }
}

