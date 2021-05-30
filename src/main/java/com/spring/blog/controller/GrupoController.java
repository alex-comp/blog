package com.spring.blog.controller;

import com.spring.blog.model.geral.Grupo;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.model.seguranca.GrupoPermissao;
import com.spring.blog.model.seguranca.Permissao;
import com.spring.blog.service.geral.GrupoService;
import com.spring.blog.service.seguranca.GrupoPermissaoService;
import com.spring.blog.service.seguranca.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(value = "/listarGrupos", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENC_GRUPO')")
    ModelAndView getListaGrupos(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {
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

    @RequestMapping(value = "/grupo", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','CADASTRO_FRUPO')")
    ModelAndView getGrupo() {
        ModelAndView mv = new ModelAndView("grupo/grupo");
        Grupo grupo = new Grupo();
        List<Permissao> permissoes = permissaoService.findAll();
        mv.addObject("permissoes", permissoes);
        mv.addObject("grupo", grupo);
        return mv;
    }

    @RequestMapping(value = "/grupo", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN','CADASTRO_FRUPO')")
    String postGrupo(RedirectAttributes attributes, Grupo grupo, Long[] idsSelecionados) {

        Grupo novoGrupo = grupoService.findByName(grupo.getNome());
        if (novoGrupo != null) {
            attributes.addFlashAttribute("mensagem", "O nome inserido já existe!");
            return "redirect:/grupo";
        }
        novoGrupo = new Grupo();
        novoGrupo.setNome(grupo.getNome());
        novoGrupo = grupoService.save(novoGrupo);
        Grupo finalNovoGrupo = novoGrupo;
        if (idsSelecionados != null) {
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

    @RequestMapping(value = "/grupoConsulta/{id}", method = RequestMethod.GET)
    ModelAndView getGrupoConsulta(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("grupo/grupoConsulta");
        Grupo grupo = grupoService.findById(id);
        List<GrupoPermissao> gruposPermissoes = grupoPermissaoService.findAllByGrupo(grupo);
        mv.addObject("gruposPermissoes", gruposPermissoes);
        mv.addObject("grupo", grupo);
        return mv;
    }

    @RequestMapping(value = "/apagarGrupo/{id}", method = RequestMethod.POST)
    String postApagarUsuario(RedirectAttributes attributes, @PathVariable("id") Long id) {
        Grupo grupo = grupoService.findById(id);

        if (!grupoPermissaoService.findAllByGrupo(grupo).isEmpty()) {
            attributes.addFlashAttribute("mensagem", "Não é possível apagar o grupo pois existem itens no banco associados à ele");
            return "redirect:/listarGrupos";
        }
        grupoService.deleteGrupo(grupo);
        return "redirect:/listarGrupos";
    }

    @RequestMapping(value = "/grupo/{id}", method = RequestMethod.GET)
    ModelAndView getGrupoEditar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("grupo/grupo");
        Grupo grupo = grupoService.findById(id);
        List<Permissao> permissoesNaoEscolhidas = permissaoService.findPermissoesNaoEscolhidas(grupo);
        List<Permissao> permissoesEscolhidas = permissaoService.findPermissoesEscolhidas(grupo);
        mv.addObject("permissoes", permissoesNaoEscolhidas);
        mv.addObject("permissoesEscolhidas", permissoesEscolhidas);
        mv.addObject("grupo", grupo);
        return mv;
    }

    @RequestMapping(value = "/grupo/{id}", method = RequestMethod.POST)
    String postGrupoEditar(@PathVariable("id") Long id, RedirectAttributes attributes, Grupo grupo, Long[] idsSelecionados, Long[] idsNaoSelecionados) {

        Grupo novoGrupo = grupoService.findByName(grupo.getNome());
        if (novoGrupo != null && novoGrupo.getId() != id) {
            attributes.addFlashAttribute("mensagem", "O nome inserido já existe!");
            return "redirect:/grupo/" + id;
        }
        novoGrupo = grupoService.findById(id);
        novoGrupo.setNome(grupo.getNome());
        novoGrupo = grupoService.save(novoGrupo);
        Grupo finalNovoGrupo = novoGrupo;
        if (idsNaoSelecionados != null) {
            Arrays.asList(idsNaoSelecionados).forEach(idNaoSelecionados -> {
                GrupoPermissao grupoPermissao = grupoPermissaoService.findByGrupoAndPermissao(finalNovoGrupo, idNaoSelecionados);
                if (grupoPermissao != null) {
                    grupoPermissaoService.deleteGrupoPermissao(grupoPermissao);
                }
            });
        }
        if (idsSelecionados != null) {
            Arrays.asList(idsSelecionados).forEach(idSelecionado -> {
                GrupoPermissao grupoPermissao = grupoPermissaoService.findByGrupoAndPermissao(finalNovoGrupo, idSelecionado);
                if (grupoPermissao == null) {
                    GrupoPermissao grupoPermissaoSalvar = new GrupoPermissao();
                    grupoPermissaoSalvar.setGrupo(finalNovoGrupo);
                    grupoPermissaoSalvar.setPermissao(permissaoService.findById(idSelecionado));
                    grupoPermissaoService.save(grupoPermissaoSalvar);
                }
            });
        }
        return "redirect:/listarGrupos";
    }
}