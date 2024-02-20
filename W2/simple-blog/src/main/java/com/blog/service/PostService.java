package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostRepository;
import com.blog.repository.PostJpaRepository;
import com.blog.vo.Post;

@Service
public class PostService {
    private static List<Post> posts;

    @Autowired
    PostRepository postRepository;

    @Autowired 
    PostJpaRepository jpaRepository;

    public Post getPost(Long id) {
        Post post = jpaRepository.findOneById(id);

        return post;
    }

    // public List<Post> getPosts() {
    //     posts = new ArrayList<>();
    //     posts.add(new Post(1L, "Mike", "Mike's Post", "Welcome to My blog"));
    //     posts.add(new Post(2L, "Jason", "It's Jason", "Hi, My name is Jason"));

    //     return posts;
    // }

    public List<Post> getPosts() {
        List<Post> postList = postRepository.findPost();

        return postList;
    }

    public List<Post> getPostsOrderByUpdtAsc() {
        List<Post> postList = postRepository.findPostOrderByUpdtAsc();

        return postList;
    }

    public List<Post> getPostsOrderByRegDesc() {
        List<Post> postList = postRepository.findPostOrderByRegDesc();

        return postList;
    }

    public List<Post> searchPostByTitle(String query) {
        List<Post> postList = postRepository.findPostLikeTitle(query);

        return postList;
    }

    public List<Post> searchPostByContent(String param) {
        List<Post> postList = postRepository.findPostLikeContent(param);

        return postList;
    }

    public boolean savePost(Post post) {
        int result = postRepository.savePost(post);
        boolean isSuccess = true;
        if(result == 0) {
            isSuccess = false;
        }
        return isSuccess;
    }

}
