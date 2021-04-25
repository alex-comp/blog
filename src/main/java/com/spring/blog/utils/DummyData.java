package com.spring.blog.utils;


import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.model.seguranca.Permissao;
import com.spring.blog.model.seguranca.UsuarioGrupo;
import com.spring.blog.service.geral.GrupoService;
import com.spring.blog.service.geral.UsuarioService;
import com.spring.blog.service.seguranca.GrupoPermissaoService;
import com.spring.blog.service.seguranca.PermissaoService;
import com.spring.blog.service.seguranca.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DummyData {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PermissaoService permissaoService;
    @Autowired
    GrupoService grupoService;
    @Autowired
    UsuarioGrupoService usuarioGrupoService;
    @Autowired
    GrupoPermissaoService grupoPermissaoService;

//    @PostConstruct
    public void createAdminUser(){
        //Criando usuario
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setNome("Admin");
        usuario.setLogin("admin");
        usuario.setSenha(new BCryptPasswordEncoder().encode("admin"));
        usuario = usuarioService.save(usuario);

        //Criando usuario sem permissao
        Usuario usuario2 = new Usuario();
        usuario2.setAtivo(true);
        usuario2.setNome("Test");
        usuario2.setLogin("test");
        usuario2.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario2);

        //Criando permissao
        Permissao permissao = new Permissao();
        permissao.setNome("CRIAR_POST");
        permissao.setDescricao("Criação de Posts");
        permissao = permissaoService.save(permissao);

        //Criando grupo
        Grupo grupo = new Grupo();
        grupo.setNome("Administrador");
        grupo = grupoService.save(grupo);

        //Atribuindo o usuario ao grupo
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        usuarioGrupo.setUsuario(usuario);
        usuarioGrupo.setGrupo(grupo);
        usuarioGrupoService.save(usuarioGrupo);

        //Atribuindo permissao ao grupo
        GrupoPermissao grupoPermissao = new GrupoPermissao();
        grupoPermissao.setGrupo(grupo);
        grupoPermissao.setPermissao(permissao);
        grupoPermissaoService.save(grupoPermissao);
    }
}
