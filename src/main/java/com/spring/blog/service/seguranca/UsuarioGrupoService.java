package com.spring.blog.service.seguranca;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.UsuarioGrupo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioGrupoService {
    List<UsuarioGrupo> findAll();
    List<UsuarioGrupo> findAllByGrupo(Grupo grupo);
    UsuarioGrupo findGruboByUsuario(Usuario usuario);
    UsuarioGrupo findById(long id);
    UsuarioGrupo save(UsuarioGrupo UsuarioGrupo);
}
