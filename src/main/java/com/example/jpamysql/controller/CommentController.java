package com.example.jpamysql.controller;

import com.example.jpamysql.model.Comment;
import com.example.jpamysql.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Operation(summary = "get all comments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Not found comment",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/comments")
    public List<Comment> getAllComments(@RequestParam(required = false, value = "postName" ) String postName,@RequestParam(required = false, value = "comment" ) String comment){
        return  commentService.getAllComments(postName, comment);
    }

    @GetMapping("/comments/{commentId}")
    public Comment findCommentById(@PathVariable(value = "commentId") int commentId){
        return  commentService.getCommentById(commentId);
    }

    @Operation(summary = "create new comment")
    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment){
        return  commentService.createComment(comment);
    }

    @Operation(summary = "delete comment")
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") int commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
