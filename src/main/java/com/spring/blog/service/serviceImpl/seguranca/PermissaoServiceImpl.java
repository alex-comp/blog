package com.spring.blog.service.serviceImpl.seguranca;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.model.seguranca.Permissao;
import com.spring.blog.repository.seguranca.GrupoPermissaoRepository;
import com.spring.blog.repository.seguranca.PermissaoRepository;
import com.spring.blog.service.seguranca.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissaoServiceImpl implements PermissaoService {
    @Autowired
    PermissaoRepository permissaoRepository;

    @Autowired
    GrupoPermissaoRepository grupoPermissaoRepository;

    @Override
    public List<Permissao> findAll() {
        return permissaoRepository.findAll();
    }

    @Override
    public List<Permissao> findPermissoesEscolhidas(Grupo grupo) {
        List<GrupoPermissao> gruposPermissoes = grupoPermissaoRepository.findAllByGrupo(grupo);
        List<Permissao> permissoes = new ArrayList<>();
        gruposPermissoes.forEach(grupoPermissao -> {
            permissoes.add(grupoPermissao.getPermissao());
        });
        return permissoes;
    }

    @Override
    public List<Permissao> findPermissoesNaoEscolhidas(Grupo grupo) {
        List<GrupoPermissao> gruposPermissoes = grupoPermissaoRepository.findAllByGrupo(grupo);
        List<Permissao> permissoesSelecionadas = new ArrayList<>();
        gruposPermissoes.forEach(grupoPermissao -> {
            permissoesSelecionadas.add(grupoPermissao.getPermissao());
        });
        List<Permissao> permissoes = permissaoRepository.findAll();
        permissoes.removeAll(permissoesSelecionadas);
        return permissoes;
    }

    @Override
    public Permissao findById(long id) {
        return permissaoRepository.findById(id).get();
    }

    @Override
    public Permissao save(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }
}
