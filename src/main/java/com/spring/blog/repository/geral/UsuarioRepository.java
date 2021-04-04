package com.spring.blog.repository.geral;

import com.spring.blog.model.geral.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByLogin(String userName);
}
