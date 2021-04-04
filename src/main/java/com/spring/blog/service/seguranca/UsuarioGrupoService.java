package com.spring.blog.service.seguranca;

import com.spring.blog.model.seguranca.UsuarioGrupo;

import java.util.List;

public interface UsuarioGrupoService {
    List<UsuarioGrupo> findAll();
    UsuarioGrupo findById(long id);
    UsuarioGrupo save(UsuarioGrupo UsuarioGrupo);
}
