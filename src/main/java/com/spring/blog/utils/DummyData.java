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

        Usuario usuario3 = new Usuario();
        usuario3.setAtivo(true);
        usuario3.setNome("Test3");
        usuario3.setLogin("test3");
        usuario3.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.setAtivo(true);
        usuario4.setNome("Test4");
        usuario4.setLogin("test4");
        usuario4.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario4);

        Usuario usuario5 = new Usuario();
        usuario5.setAtivo(true);
        usuario5.setNome("Test5");
        usuario5.setLogin("test5");
        usuario5.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario5);

        Usuario usuario6 = new Usuario();
        usuario6.setAtivo(true);
        usuario6.setNome("Test6");
        usuario6.setLogin("test6");
        usuario6.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario6);

        Usuario usuario7 = new Usuario();
        usuario7.setAtivo(true);
        usuario7.setNome("Test7");
        usuario7.setLogin("test7");
        usuario7.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario7);

        Usuario usuario8 = new Usuario();
        usuario8.setAtivo(true);
        usuario8.setNome("Test8");
        usuario8.setLogin("test8");
        usuario8.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario8);

        Usuario usuario9 = new Usuario();
        usuario9.setAtivo(true);
        usuario9.setNome("Test9");
        usuario9.setLogin("test9");
        usuario9.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario9);

        Usuario usuario10 = new Usuario();
        usuario10.setAtivo(true);
        usuario10.setNome("Test10");
        usuario10.setLogin("test10");
        usuario10.setSenha(new BCryptPasswordEncoder().encode("test"));
        usuarioService.save(usuario10);

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
