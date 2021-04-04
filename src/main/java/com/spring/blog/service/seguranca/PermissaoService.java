package com.spring.blog.service.seguranca;

import com.spring.blog.model.seguranca.Permissao;

import java.util.List;

public interface PermissaoService {

    List<Permissao> findAll();
    Permissao findById(long id);
    Permissao save(Permissao permissao);
}
