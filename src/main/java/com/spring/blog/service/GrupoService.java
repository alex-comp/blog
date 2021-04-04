package com.spring.blog.service;

import com.spring.blog.model.Grupo;

import java.util.List;

public interface GrupoService {
    List<Grupo> findAll();
    Grupo findById(long id);
    Grupo save(Grupo grupo);
}
