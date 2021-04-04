package com.spring.blog.service.serviceImpl;

import com.spring.blog.model.Usuario;
import com.spring.blog.repository.UsuarioRepository;
import com.spring.blog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
