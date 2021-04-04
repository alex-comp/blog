package com.spring.blog.service.serviceImpl.seguranca;

import com.spring.blog.model.seguranca.Permissao;
import com.spring.blog.repository.seguranca.PermissaoRepository;
import com.spring.blog.service.seguranca.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoServiceImpl implements PermissaoService {
    @Autowired
    PermissaoRepository permissaoRepository;

    @Override
    public List<Permissao> findAll() {
        return permissaoRepository.findAll();
    }

    @Override
    public Permissao findById(long id) {
        return permissaoRepository.findById(id).get();
    }

    @Override
    public Permissao save(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }
}
