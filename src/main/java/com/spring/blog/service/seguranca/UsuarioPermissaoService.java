package com.spring.blog.service.seguranca;

import com.spring.blog.model.seguranca.UsuarioPermissao;

import java.util.List;

public interface UsuarioPermissaoService {

    List<UsuarioPermissao> findAll();
    UsuarioPermissao findById(long id);
    UsuarioPermissao findByUserName(String userName);
    UsuarioPermissao save(UsuarioPermissao usuarioPermissao);
}
