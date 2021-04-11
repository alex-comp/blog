package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.model.geral.Usuario;
import com.spring.blog.security.GpUserDetails;
import com.spring.blog.service.PostService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.security.Security;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = postService.findAllByDataDesc();
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = "/post/{id}",method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/newPost",method = RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
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
