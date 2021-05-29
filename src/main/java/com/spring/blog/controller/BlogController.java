package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.security.GpUserDetails;
import com.spring.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BlogController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getRoot(){
        return "redirect:/posts";
    }

    @RequestMapping(value = "/entrar",method = RequestMethod.GET)
    public String entrar(){
        return "loginBlog";
    }

    @RequestMapping(value = "/posts",method = RequestMethod.GET)
    public ModelAndView getPosts(@RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size){
        ModelAndView mv = new ModelAndView("blog/posts");
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

    @RequestMapping(value = "/post/{id}",method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("blog/postDetails");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/newPost",method = RequestMethod.GET)
    @PreAuthorize("hasRole('CRIAR_POST')")
    public String getPostForm(){
        return "blog/postForm";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    @PreAuthorize("hasRole('CRIAR_POST')")
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes){
        GpUserDetails Usuario = (GpUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifique se os campos obrigatorios foram preenchidos");
            return "redirect:/newPost";
        }
        post.setAutor(Usuario.getNome());
        post.setData(new Date());
        postService.save(post);
        return "redirect:/posts";
    }
}
