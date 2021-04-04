package com.spring.blog.service.serviceImpl.seguranca;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.repository.seguranca.GrupoPermissaoRepository;
import com.spring.blog.service.seguranca.GrupoPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GrupoPermissaoServiceImpl implements GrupoPermissaoService {
    @Autowired
    GrupoPermissaoRepository grupoPermissaoRepository;

    @Override
    public List<GrupoPermissao> findAll() {
        return grupoPermissaoRepository.findAll();
    }

    @Override
    public GrupoPermissao findById(long id) {
        return grupoPermissaoRepository.findById(id).get();
    }

    @Override
    public GrupoPermissao save(GrupoPermissao grupoPermissao) {
        return grupoPermissaoRepository.save(grupoPermissao);
    }
}
