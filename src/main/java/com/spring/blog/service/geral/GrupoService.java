package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Grupo;

import java.util.List;

public interface GrupoService {
    List<Grupo> findAll();
    Grupo findById(long id);
    Grupo save(Grupo grupo);
}
