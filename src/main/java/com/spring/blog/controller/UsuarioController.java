package com.spring.blog.controller;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.UsuarioGrupo;
import com.spring.blog.service.geral.GrupoService;
import com.spring.blog.service.geral.UsuarioService;
import com.spring.blog.service.seguranca.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    UsuarioGrupoService usuarioGrupoService;

    @RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
    ModelAndView getListaUsuarios(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        ModelAndView mv = new ModelAndView("listaUsuarios");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Usuario> usuarios = usuarioService.findAllPaginated(currentPage, pageSize);
        int totalPages = usuarios.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            mv.addObject("qtdPages", pageNumbers);
        }
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.GET)
    ModelAndView getUsuario(){
        ModelAndView mv = new ModelAndView("usuario");
        Usuario usuario = new Usuario();
        List<Grupo> grupos = grupoService.findAll();
        Grupo grupoId = new Grupo();
        mv.addObject("grupos",grupos);
        mv.addObject("grupoId",grupoId);
        mv.addObject("usuario",usuario);
        return mv;
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.POST)
    String postUsuario(Usuario usuario, Grupo grupo){
        Usuario novoUsuario = new Usuario();
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode( usuario.getSenha()));
        novoUsuario.setAtivo(usuario.getAtivo());
        novoUsuario = usuarioService.save(novoUsuario);
        usuarioGrupo.setGrupo(grupoService.findById(grupo.getId()));
        usuarioGrupo.setUsuario(novoUsuario);
        usuarioGrupoService.save(usuarioGrupo);
        return "redirect:/listarUsuarios";
    }
}
