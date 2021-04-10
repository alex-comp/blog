package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    List<Usuario> findAll();
    Usuario findByUserName(String userName);
    Usuario findById(long id);
    Usuario save(Usuario usuario);
}
