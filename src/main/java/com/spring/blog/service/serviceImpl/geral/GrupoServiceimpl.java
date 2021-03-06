package com.spring.blog.service.serviceImpl.geral;

import com.spring.blog.model.Post;
import com.spring.blog.model.geral.Grupo;
import com.spring.blog.repository.geral.GrupoRepository;
import com.spring.blog.service.geral.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public void deleteGrupo(Grupo grupo) {
        grupoRepository.delete(grupo);
    }

    @Override
    public Grupo findByName(String nome) {
        return grupoRepository.findGrupoByNome(nome);
    }

    @Override
    public Page<Grupo> findAllPaginated(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage - 1,pageSize);
        Long total = grupoRepository.count();
        List<Grupo> grupos = grupoRepository.findAllOrderByNomeAsc(pageable);
        return new PageImpl<Grupo>(grupos, pageable, total);
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
