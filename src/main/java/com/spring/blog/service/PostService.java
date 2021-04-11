package com.spring.blog.service;

import com.spring.blog.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findAllByDataDesc();
    Post findById(long id);
    Post save(Post post);
}
