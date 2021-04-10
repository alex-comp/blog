package com.spring.blog.service.seguranca;

import com.spring.blog.model.seguranca.Permissao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissaoService {

    List<Permissao> findAll();
    Permissao findById(long id);
    Permissao save(Permissao permissao);
}
