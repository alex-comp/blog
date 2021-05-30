package com.spring.blog.repository.seguranca;

import com.spring.blog.model.seguranca.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao,Long> {
    @Query("SELECT p FROM Permissao AS p ORDER BY p.descricao ASC")
    List<Permissao> findAllOrderAsc();
}
