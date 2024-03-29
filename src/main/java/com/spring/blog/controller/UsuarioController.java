package com.spring.blog.controller;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.UsuarioGrupo;
import com.spring.blog.service.geral.GrupoService;
import com.spring.blog.service.geral.UsuarioService;
import com.spring.blog.service.seguranca.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENC_USUARIO')")
    ModelAndView getListaUsuarios(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        ModelAndView mv = new ModelAndView("usuario/listaUsuarios");
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

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','CADASTRO_USUARIO')")
    ModelAndView getUsuario() {
        ModelAndView mv = new ModelAndView("usuario/usuario");
        Usuario usuario = new Usuario();
        List<Grupo> grupos = grupoService.findAll();
        Grupo grupoId = new Grupo();
        mv.addObject("grupos", grupos);
        mv.addObject("grupoId", grupoId);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN','CADASTRO_USUARIO')")
    String postUsuario(RedirectAttributes attributes, Usuario usuario, Grupo grupo) {
        Usuario novoUsuario = usuarioService.findByUserName(usuario.getLogin());
        if (novoUsuario != null) {
            attributes.addFlashAttribute("mensagem", "O login inserido já existe!");
            return "redirect:/usuario";
        }

        novoUsuario = usuarioService.findByNome(usuario.getNome());

        if (novoUsuario != null) {
            attributes.addFlashAttribute("mensagem", "O nome inserido já existe!");
            return "redirect:/usuario";
        }

        novoUsuario = new Usuario();
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        novoUsuario.setAtivo(usuario.getAtivo());
        novoUsuario = usuarioService.save(novoUsuario);
        if (grupo.getId() != null) {
            usuarioGrupo.setGrupo(grupoService.findById(grupo.getId()));
            usuarioGrupo.setUsuario(novoUsuario);
            usuarioGrupoService.save(usuarioGrupo);
        }
        return "redirect:/listarUsuarios";
    }

    @RequestMapping(value = "/usuarioConsulta/{id}", method = RequestMethod.GET)
    ModelAndView getUsuarioConsulta(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("usuario/usuarioConsulta");
        Usuario usuario = usuarioService.findById(id);
        UsuarioGrupo usuarioGrupo = usuarioGrupoService.findGruboByUsuario(usuario);
        Grupo grupo = usuarioGrupo == null ? new Grupo() : usuarioGrupo.getGrupo();
        mv.addObject("grupo", grupo);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value = "/apagarUsuario/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN','DELETE_USER')")
    String postApagarUsuario(RedirectAttributes attributes, @PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuarioGrupoService.findGruboByUsuario(usuario) != null) {
            attributes.addFlashAttribute("mensagem", "Não é possível apagar o usuário pois existem itens no banco associados à ele");
            return "redirect:/listarUsuarios";
        }
        usuarioService.deleteUsuario(usuario);
        return "redirect:/listarUsuarios";
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','EDIT_USER')")
    ModelAndView getEditarUsuario(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("usuario/usuario");
        Usuario usuario = usuarioService.findById(id);
        List<Grupo> grupos = grupoService.findAll();
        UsuarioGrupo usuarioGrupo = usuarioGrupoService.findGruboByUsuario(usuario);
        Grupo grupoId = usuarioGrupo == null ? new Grupo() : usuarioGrupo.getGrupo();
        mv.addObject("grupos", grupos);
        mv.addObject("grupoId", grupoId);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN','EDIT_USER')")
    String postUsuario(@PathVariable("id") Long id,RedirectAttributes attributes, Usuario usuario, Grupo grupo) {
        Usuario novoUsuario = usuarioService.findByUserName(usuario.getLogin());
        if (novoUsuario != null && novoUsuario.getId() != id) {
            attributes.addFlashAttribute("mensagem", "O login inserido já existe!");
            return "redirect:/usuario/" + id;
        }

        novoUsuario = usuarioService.findByNome(usuario.getNome());

        if (novoUsuario != null && novoUsuario.getId() != id) {
            attributes.addFlashAttribute("mensagem", "O nome inserido já existe!");
            return "redirect:/usuario/" + id;
        }

        novoUsuario = usuarioService.findById(id);
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        novoUsuario.setAtivo(usuario.getAtivo());
        novoUsuario = usuarioService.save(novoUsuario);
        if (grupo.getId() != null) {
            usuarioGrupo.setGrupo(grupoService.findById(grupo.getId()));
            usuarioGrupo.setUsuario(novoUsuario);
            usuarioGrupoService.save(usuarioGrupo);
        }
        return "redirect:/listarUsuarios";
    }
}
