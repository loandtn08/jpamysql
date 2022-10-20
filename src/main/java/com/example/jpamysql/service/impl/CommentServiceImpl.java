package com.example.jpamysql.service.impl;

import com.example.jpamysql.model.Comment;
import com.example.jpamysql.repository.CommentRepository;
import com.example.jpamysql.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.jpamysql.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public List<Comment> getAllComments(String postName, String comment) {
        if((postName != null && !"".equals(postName))|| (comment != null && !"".equals(comment))){
            return commentRepository.findByPostNameAndCommentName(postName, comment);
        }else{
            return commentRepository.findAll();
        }

    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
    }

    @Override
    public Comment updateComment(int commentId, Comment commentDetail) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

        comment.setPostName(commentDetail.getPostName());
        comment.setComment(commentDetail.getComment());

        Comment updatedComment = commentRepository.save(comment);
        return  updatedComment;

    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        commentRepository.delete(comment);
    }
}
