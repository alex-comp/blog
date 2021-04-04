package com.spring.blog.service.serviceImpl.geral;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.repository.geral.GrupoRepository;
import com.spring.blog.service.geral.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceimpl implements GrupoService {
    @Autowired
    GrupoRepository grupoRepository;

    @Override
    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    @Override
    public Grupo findById(long id) {
        return grupoRepository.findById(id).get();
    }

    @Override
    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }
}
