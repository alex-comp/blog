package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> findAll();
    void deleteUsuario(Usuario usuario);
    Page<Usuario> findAllPaginated(int currentPage,int pageSize);
    Usuario findByUserName(String userName);
    Usuario findByNome(String nome);
    Usuario findById(long id);
    Usuario save(Usuario usuario);
}
