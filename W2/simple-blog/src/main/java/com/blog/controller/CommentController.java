package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public Result savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
        Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());
        boolean isSuccess = commentService.saveComment(comment);
        if (isSuccess) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return new Result(200, "댓글이 등록되었습니다.");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "댓글 등록에 실패했습니다.");
        }
    }

    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam("post_id") Long postId) {
        List<Comment> comments = commentService.getCommentList(postId);
        return comments;
    }

    @GetMapping("/comment")
    public Comment getComment(@RequestParam("id") Long id) {
        Comment comment = commentService.getComment(id);
        return comment;
    }

    @DeleteMapping("/comment")
    public Result deleteComment(HttpServletResponse response, @RequestParam("id") Long id) {
        boolean isSuccess = commentService.deleteComment(id);
        if (isSuccess) {
            response.setStatus(HttpServletResponse.SC_OK);
            return new Result(200, "success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "failed");
        }
    }

    @GetMapping("/comments/search")
    public List<Comment> searchCommentsByPost(@RequestParam("post_id") Long id, @RequestParam("query") String query) {
        List<Comment> comments = commentService.searchCommentByPostIdAndComment(id, query);
        return comments;
    }
    
    
    

}
