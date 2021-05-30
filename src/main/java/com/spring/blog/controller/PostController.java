package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/listarPosts",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENC_POST')")
    ModelAndView getListarPosts(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size){
        ModelAndView mv = new ModelAndView("post/listaPosts");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Post> posts = postService.findAllPaginated(currentPage, pageSize);
        int totalPages = posts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            mv.addObject("qtdPages", pageNumbers);
        }
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = "/apagarPost/{id}",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN','DELETE_POST')")
    String postApagarPost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return "redirect:/listarPosts";
    }
}
