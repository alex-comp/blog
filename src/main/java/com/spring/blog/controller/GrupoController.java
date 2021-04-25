package com.spring.blog.controller;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.service.geral.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @RequestMapping(value = "/listarGrupos",method = RequestMethod.GET)
    ModelAndView getListaGrupos(){
        ModelAndView mv = new ModelAndView("listaGrupos");
        List<Grupo> grupos = grupoService.findAll();
        mv.addObject("grupos",grupos);
        return mv;
    }
}
