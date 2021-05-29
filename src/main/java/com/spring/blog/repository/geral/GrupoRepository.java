package com.spring.blog.repository.geral;

import com.spring.blog.model.geral.Grupo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {

    @Query("SELECT gr FROM Grupo AS gr ORDER BY gr.nome ASC")
    List<Grupo> findAllOrderByNomeAsc(Pageable pageable);

    Grupo findGrupoByNome(String nome);
}
