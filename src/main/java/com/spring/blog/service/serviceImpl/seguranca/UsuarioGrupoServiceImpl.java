package com.spring.blog.service.serviceImpl.seguranca;

import com.spring.blog.model.seguranca.UsuarioGrupo;
import com.spring.blog.repository.seguranca.UsuarioGrupoRepository;
import com.spring.blog.service.seguranca.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioGrupoServiceImpl implements UsuarioGrupoService {
    @Autowired
    UsuarioGrupoRepository usuarioGrupoRepository;

    @Override
    public List<UsuarioGrupo> findAll() {
        return usuarioGrupoRepository.findAll();
    }

    @Override
    public UsuarioGrupo findById(long id) {
        return usuarioGrupoRepository.findById(id).get();
    }

    @Override
    public UsuarioGrupo save(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }
}
