package com.spring.blog.security;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.service.geral.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GpUserDetailsService implements UserDetailsService {
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

            GpUserDetails userDetails = buscarUsuario(login);
            return userDetails;
    }

    public GpUserDetails buscarUsuario(String login) {
        Usuario usuario = usuarioService.findByUserName(login);
        if(usuario == null){
            return new GpUserDetails(null, null, null, false);
        }
        String nome = usuario.getNome();
        String password = usuario.getSenha();
        boolean ativo = usuario.getAtivo();

        return new GpUserDetails(nome, login, password, ativo);
    }
}
