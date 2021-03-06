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
    public void createAdminUser() {
        //Criando usuario
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        usuario.setNome("Admin");
        usuario.setLogin("admin");
        usuario.setSenha(new BCryptPasswordEncoder().encode("admin"));
        usuario = usuarioService.save(usuario);

        //Criando permissoes
        Permissao permissao = new Permissao();
        permissao.setNome("GERENC_USUARIO");
        permissao.setDescricao("Listar Usuários");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("CADASTRO_USUARIO");
        permissao.setDescricao("Cadastrar Usuários");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("GERENC_GRUPO");
        permissao.setDescricao("Listar Grupo");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("CADASTRO_FRUPO");
        permissao.setDescricao("Cadastrar Grupo");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("GERENC_POST");
        permissao.setDescricao("Listar Posts");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("CRIAR_POST");
        permissao.setDescricao("Criar Posts");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("EDIT_USER");
        permissao.setDescricao("Editar Usuario");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("DELETE_USER");
        permissao.setDescricao("Apagar Usuario");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("EDIT_GRUPO");
        permissao.setDescricao("Editar Grupo");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("DELETE_GRUPO");
        permissao.setDescricao("Apagar Grupo");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("EDIT_POST");
        permissao.setDescricao("Editar Post");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("DELETE_POST");
        permissao.setDescricao("Apagar Post");
        permissaoService.save(permissao);

        permissao = new Permissao();
        permissao.setNome("ADMIN");
        permissao.setDescricao("Controle de Tudo");
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
