package com.api.repository;
import com.api.model.Usuario;

// UsuarioRepository.java
import java.util.*;

public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuarioAtualizado.getId())) {
                usuarios.set(i, usuarioAtualizado);
                return;
            }
        }
    }

    public void remover(UUID id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}
