package com.spring.blog.service.seguranca;

import com.spring.blog.model.seguranca.GrupoPermissao;

import java.util.List;

public interface GrupoPermissaoService {
    List<GrupoPermissao> findAll();
    GrupoPermissao findById(long id);
    GrupoPermissao save(GrupoPermissao grupoPermissao);
}
