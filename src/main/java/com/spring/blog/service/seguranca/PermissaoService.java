package com.spring.blog.service.seguranca;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.Permissao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissaoService {

    List<Permissao> findAll();
    List<Permissao> findPermissoesEscolhidas(Grupo grupo);
    List<Permissao> findPermissoesNaoEscolhidas(Grupo grupo);
    Permissao findById(long id);
    Permissao save(Permissao permissao);
}
