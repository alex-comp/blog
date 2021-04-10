package com.spring.blog.repository.seguranca;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoPermissaoRepository extends JpaRepository<GrupoPermissao,Long> {
    @Query("SELECT gp FROM GrupoPermissao AS gp INNER JOIN Grupo AS g ON gp.grupo = g " +
            "INNER JOIN Permissao AS p ON gp.permissao = p " +
            "INNER JOIN UsuarioGrupo AS ug ON ug.grupo = g " +
            "INNER JOIN Usuario AS u ON ug.usuario = u WHERE u = ?1 ")
    List<GrupoPermissao> findAllByUsuario(Usuario usuario);
}
