package com.spring.blog.service.serviceImpl.geral;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.repository.geral.UsuarioRepository;
import com.spring.blog.service.geral.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Usuario> findAllPaginated(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage - 1,pageSize);
        Long total = usuarioRepository.count();
        List<Usuario> usuarios = usuarioRepository.findAllOrderByNome(pageable);
        return new PageImpl<Usuario>(usuarios, pageable, total);
    }

    @Override
    public Usuario findByUserName(String userName){
        return usuarioRepository.findByLogin(userName);
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
