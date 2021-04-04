package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();
    Usuario findByUserName(String userName);
    Usuario findById(long id);
    Usuario save(Usuario usuario);
}
