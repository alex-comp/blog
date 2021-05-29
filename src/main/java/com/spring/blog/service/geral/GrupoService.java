package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrupoService {
    List<Grupo> findAll();
    void deleteGrupo(Grupo grupo);
    Grupo findByName(String nome);
    Page<Grupo> findAllPaginated(int currentPage, int pageSize);
    Grupo findById(long id);
    Grupo save(Grupo grupo);
}
