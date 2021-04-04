package com.spring.blog.repository.seguranca;

import com.spring.blog.model.seguranca.UsuarioPermissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao,Long> {
    UsuarioPermissao findByUsuario_Login(String userName);
}
