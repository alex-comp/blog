package com.spring.blog.controller;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.model.seguranca.Permissao;
import com.spring.blog.service.geral.GrupoService;
import com.spring.blog.service.seguranca.GrupoPermissaoService;
import com.spring.blog.service.seguranca.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @Autowired
    PermissaoService permissaoService;

    @Autowired
    GrupoPermissaoService grupoPermissaoService;

    @RequestMapping(value = "/listarGrupos",method = RequestMethod.GET)
    ModelAndView getListaGrupos(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size){
        ModelAndView mv = new ModelAndView("grupo/listaGrupos");
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

    @RequestMapping(value = "/grupo",method = RequestMethod.GET)
    ModelAndView getGrupo(){
        ModelAndView mv = new ModelAndView("grupo/grupo");
        Grupo grupo = new Grupo();
        List<Permissao> permissoes = permissaoService.findAll();
        mv.addObject("permissoes",permissoes);
        mv.addObject("grupo", grupo);
        return mv;
    }

    @RequestMapping(value = "/grupo",method = RequestMethod.POST)
    String postGrupo(RedirectAttributes attributes, Grupo grupo, Long[] idsSelecionados){

        Grupo novoGrupo = grupoService.findByName(grupo.getNome());
        if(novoGrupo != null){
            attributes.addFlashAttribute("mensagem","O nome inserido já existe!");
            return "redirect:/grupo";
        }
        novoGrupo = new Grupo();
        novoGrupo.setNome(grupo.getNome());
        novoGrupo = grupoService.save(novoGrupo);
        Grupo finalNovoGrupo = novoGrupo;
        if(idsSelecionados != null) {
            Arrays.asList(idsSelecionados).forEach(id -> {
                permissaoService.findById(id);
                GrupoPermissao grupoPermissao = new GrupoPermissao();
                grupoPermissao.setGrupo(finalNovoGrupo);
                grupoPermissao.setPermissao(permissaoService.findById(id));
                grupoPermissaoService.save(grupoPermissao);
            });
        }
        return "redirect:/listarGrupos";
    }

    @RequestMapping(value = "/grupoConsulta/{id}",method = RequestMethod.GET)
    ModelAndView getGrupoConsulta(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("grupo/grupoConsulta");
        Grupo grupo = grupoService.findById(id);
        List<GrupoPermissao> gruposPermissoes = grupoPermissaoService.findAllByGrupo(grupo);
        mv.addObject("gruposPermissoes",gruposPermissoes);
        mv.addObject("grupo", grupo);
        return mv;
    }
}