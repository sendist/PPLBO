package com.blog.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.vo.Post;

@Repository("PostJpaRepository")
public interface PostJpaRepository extends JpaRepository<Post, Serializable> {
    Post findOneById(Long id);
}
