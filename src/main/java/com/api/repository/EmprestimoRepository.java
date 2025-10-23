package com.api.repository;
import com.api.model.Emprestimo;

// EmprestimoRepository.java
import java.util.*;

public class EmprestimoRepository {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void registrar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public Emprestimo buscarPorId(UUID id) {
        return emprestimos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Emprestimo> buscarPorUsuario(UUID usuarioId) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId().equals(usuarioId)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public void remover(UUID id) {
        emprestimos.removeIf(e -> e.getId().equals(id));
    }

    public List<Emprestimo> listarTodos() {
        return new ArrayList<>(emprestimos);
    }
}
