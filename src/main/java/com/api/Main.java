package com.api;

import com.api.model.Usuario;
import com.api.repository.UsuarioRepository;
import com.api.services.UserService;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class Main {
    
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UserService userService = new UserService(usuarioRepository);

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
    }
}