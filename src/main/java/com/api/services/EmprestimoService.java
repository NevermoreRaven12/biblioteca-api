package com.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.api.model.Emprestimo;
import com.api.model.Livro;
import com.api.model.Usuario;
import com.api.repository.EmprestimoRepository;
import com.api.repository.LivroRepository;
import com.api.repository.UsuarioRepository;

public class EmprestimoService {
    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;
    private LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository){
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean fazerEmprestimo(UUID usuarioId, UUID livroId){
        Usuario usuario = usuarioRepository.buscarPorId(usuarioId);
        Livro livro = livroRepository.buscarPorId(livroId);

        if (usuario == null || livro == null || !livro.isDisponivel()){
            return false;
        }

        livro.setDisponivel(false);
        livroRepository.atualizar(livro);
        Emprestimo emprestimo = new Emprestimo(usuario,livro, LocalDateTime.now());
        emprestimoRepository.registrar(emprestimo);
        return true;
    }

    public boolean devolverLivro(UUID emprestimoId){
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);
        if (emprestimo == null){return false;}
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.atualizar(livro);
        emprestimoRepository.remover(emprestimoId);
        return true;
    }

    public List<Emprestimo> verEmprestimos(){
        return emprestimoRepository.listarTodos();
    }

}
