package com.spring.blog.repository.geral;

import com.spring.blog.model.geral.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByLogin(String userName);

    @Query("SELECT u FROM Usuario AS u ORDER BY u.nome ASC")
    List<Usuario> findAllOrderByNome(Pageable pageable);
}
