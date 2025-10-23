package com.api.services;
import java.util.List;
import java.util.UUID;

import com.api.model.Usuario;
import com.api.repository.UsuarioRepository;

public class UserService {
    private UsuarioRepository repository;

    public UserService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(String username, String password){
        if (username == null || username.isBlank()){
            throw new IllegalArgumentException("Username é obrigatório!");
        }

        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("Senha é obrigatória!");
        }
        Usuario usuario = new Usuario(username,password);
        repository.adicionar(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios(){
        return repository.listarTodos();
    }

    public Usuario buscarUsuario(UUID id){
        return repository.buscarPorId(id);
    }

    public boolean deletarUsuario(UUID id){
        Usuario usuario = repository.buscarPorId(id);
        if (usuario !=null){
            repository.remover(id);
            return true;
        }
        return false;
    }
}
