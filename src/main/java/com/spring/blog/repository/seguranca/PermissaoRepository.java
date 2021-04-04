package com.spring.blog.repository.seguranca;

import com.spring.blog.model.seguranca.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao,Long> {
}
