package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.model.geral.Grupo;
import com.spring.blog.service.geral.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @RequestMapping(value = "/listarGrupos",method = RequestMethod.GET)
    ModelAndView getListaGrupos(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size){
        ModelAndView mv = new ModelAndView("listaGrupos");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Grupo> grupos = grupoService.findAllPaginated(currentPage, pageSize);
        int totalPages = grupos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            mv.addObject("qtdPages", pageNumbers);
        }
        mv.addObject("grupos", grupos);
        return mv;
    }
}
