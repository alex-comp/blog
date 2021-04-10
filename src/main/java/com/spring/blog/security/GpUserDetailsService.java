package com.spring.blog.security;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.service.geral.UsuarioService;
import com.spring.blog.service.seguranca.GrupoPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class GpUserDetailsService implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoPermissaoService grupoPermissaoService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.findByUserName(login);
        GpUserDetails userDetails = new GpUserDetails(
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo()
        );
        userDetails.getAuthorities().addAll(buscarPermissoesPorGrupoUsuario(usuario));

        return userDetails;
    }

    public Collection<GrantedAuthority> buscarPermissoesPorGrupoUsuario(Usuario usuario){
        List<GrantedAuthority> permissoes = new ArrayList<>();
        grupoPermissaoService.findAllByUsuario(usuario).forEach(permissao ->{
            permissoes.add(new SimpleGrantedAuthority((permissao.getPermissao().getNome())));
        });
        return permissoes;
    }
}
