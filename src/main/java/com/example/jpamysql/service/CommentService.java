package com.example.jpamysql.service;

import com.example.jpamysql.model.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllComments(String postName, String comment);
    public Comment createComment(Comment comment);
    public  Comment getCommentById(int commentId);
    public Comment updateComment(int commentId, Comment comment);
    public void deleteComment(int commentId);

}
