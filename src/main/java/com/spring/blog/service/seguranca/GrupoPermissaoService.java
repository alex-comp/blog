package com.spring.blog.service.seguranca;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrupoPermissaoService {
    List<GrupoPermissao> findAll();
    GrupoPermissao findById(long id);
    List<GrupoPermissao> findAllByUsuario(Usuario usuario);
    GrupoPermissao save(GrupoPermissao grupoPermissao);
}
