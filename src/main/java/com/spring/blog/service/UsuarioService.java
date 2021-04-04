package com.spring.blog.service;

import com.spring.blog.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();
    Usuario findById(long id);
    Usuario save(Usuario usuario);
}
