package com.spring.blog.controller;

import com.spring.blog.model.geral.Usuario;
import com.spring.blog.service.geral.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    ModelAndView getUsuarios() {
        ModelAndView mv = new ModelAndView("usuarios");
        List<Usuario> usuarios = usuarioService.findAll();
        mv.addObject("usuarios",usuarios);
        return mv;
    }
}
