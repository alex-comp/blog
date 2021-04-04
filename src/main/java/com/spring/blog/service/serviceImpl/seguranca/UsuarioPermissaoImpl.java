package com.spring.blog.service.serviceImpl.seguranca;

import com.spring.blog.model.seguranca.UsuarioPermissao;
import com.spring.blog.repository.seguranca.UsuarioPermissaoRepository;
import com.spring.blog.service.seguranca.UsuarioPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioPermissaoImpl implements UsuarioPermissaoService {
    @Autowired
    UsuarioPermissaoRepository usuarioPermissaoRepository;

    @Override
    public List<UsuarioPermissao> findAll() {
        return usuarioPermissaoRepository.findAll();
    }

    @Override
    public UsuarioPermissao findById(long id) {
        return usuarioPermissaoRepository.findById(id).get();
    }

    @Override
    public UsuarioPermissao save(UsuarioPermissao usuarioPermissao) {
        return usuarioPermissaoRepository.save(usuarioPermissao);
    }
}
