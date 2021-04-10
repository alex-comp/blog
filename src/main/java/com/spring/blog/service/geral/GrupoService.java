package com.spring.blog.service.geral;

import com.spring.blog.model.geral.Grupo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrupoService {
    List<Grupo> findAll();
    Grupo findById(long id);
    Grupo save(Grupo grupo);
}
