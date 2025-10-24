package com.api;

import com.api.model.Emprestimo;
import com.api.model.Livro;
import com.api.model.Usuario;
import com.api.repository.EmprestimoRepository;
import com.api.repository.LivroRepository;
import com.api.repository.UsuarioRepository;
import com.api.services.EmprestimoService;
import com.api.services.LivroService;
import com.api.services.UserService;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class Main {
    
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UserService userService = new UserService(usuarioRepository);
        LivroRepository livroRepository = new LivroRepository();
        LivroService livroService = new LivroService(livroRepository);
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
        EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, usuarioRepository, livroRepository);

        var app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
            config.staticFiles.add("/public");
        }).start(7000);

        app.post("/", ctx -> {
            try{
                Usuario user = ctx.bodyAsClass(Usuario.class);
                userService.cadastrarUsuario(user.getUsername(), user.getPassword());
                ctx.status(201).result("Usuário cadastrado.");
            } catch (IllegalArgumentException e){
                ctx.status(400).result(e.getMessage());
            }
        });

        app.get("/home", ctx -> {
            ctx.redirect("/index.html");
        });

        app.get("/livros", ctx -> {
            String author = ctx.queryParam("author");
            String title = ctx.queryParam("titulo");
            var livros = livroService.listarLivros().stream()
            .filter(l -> author == null || l.getAutor().equalsIgnoreCase(author))
            .filter(l -> title == null || l.getTitulo().equalsIgnoreCase(title))
            .toList();

            ctx.json(livros);
        });
        app.post("/livros", ctx -> {
            try {
            Livro livro = ctx.bodyAsClass(Livro.class);
            Livro livroCadastrado = livroService.cadastrarLivro(livro.getTitulo(), livro.getAutor());
            ctx.status(201).json(livroCadastrado);
            } catch (IllegalArgumentException e){
                ctx.status(400).result(e.getMessage());
            }
        });

        app.post("/emprestimos", ctx -> {
            Emprestimo emprestimo = ctx.bodyAsClass(Emprestimo.class);
            Livro livro = livroService.buscarLivro(emprestimo.getLivro().getId());
            Usuario usuario = userService.buscarUsuario(emprestimo.getUsuario().getId());

            try{
                
                    emprestimoService.fazerEmprestimo(livro.getId(), usuario.getId());
                    ctx.status(201).result("Emprestimo realizado.");
            } catch (IllegalArgumentException e){
                ctx.status(400).result(e.getMessage());
            }
        });
    }
}