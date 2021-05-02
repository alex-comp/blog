package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> findAll();
    Page<Usuario> findAllPaginated(int currentPage,int pageSize);
    Usuario findByUserName(String userName);
    Usuario findById(long id);
    Usuario save(Usuario usuario);
}
