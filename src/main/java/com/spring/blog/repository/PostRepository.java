package com.spring.blog.repository;

import com.spring.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT post FROM Post AS post ORDER BY post.data DESC")
    List<Post> findAllOrderByDataDesc();

    // Native query
//    @Query(value = "SELECT * FROM tb_post AS post ORDER BY post.data DESC",nativeQuery = true)
//    List<Post> findAllOrderByDataDesc();
}
