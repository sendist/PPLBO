package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;

@Service
public class CommentService {

    @Autowired
    CommentJpaRepository commentRepository;

    public boolean saveComment(Comment comment) {
        Comment result = commentRepository.save(comment);
        boolean isSuccess = true;
        if (result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public List<Comment> getCommentList(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdOrderByRegDateDesc(postId);

        return comments;
    }

    public Comment getComment(Long id) {
        Comment comment = commentRepository.findOneById(id);
        return comment;
    }

    public boolean deleteComment(Long id) {
        Comment result = commentRepository.findOneById(id);
        if(result == null) {
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }

    public List<Comment> searchCommentByPostIdAndComment(Long id, String query) {
        List<Comment> comments = commentRepository.findByPostIdAndCommentContainingOrderByRegDateDesc(id, query);
        return comments;
    }

}
