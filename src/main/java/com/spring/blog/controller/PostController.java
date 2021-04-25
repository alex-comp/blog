package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/listarPosts",method = RequestMethod.GET)
    ModelAndView getListarPosts(){
        ModelAndView mv = new ModelAndView("listaPosts");
        List<Post> posts = postService.findAll();
        mv.addObject("posts",posts);
        return mv;
    }
}
