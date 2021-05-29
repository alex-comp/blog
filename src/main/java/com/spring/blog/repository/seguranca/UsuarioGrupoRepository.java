package com.spring.blog.repository.seguranca;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.model.seguranca.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo,Long> {
    UsuarioGrupo findUsuarioGrupoByUsuario(Usuario usuario);
}
