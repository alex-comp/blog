package com.spring.blog.service;

import com.spring.blog.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Page<Post> findAllPaginated(int currentPage, int pageSize);
    Post findById(long id);
    Post save(Post post);
}
