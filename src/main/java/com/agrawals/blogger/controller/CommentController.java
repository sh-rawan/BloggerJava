package com.agrawals.blogger.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrawals.blogger.dto.CommentDto;
import com.agrawals.blogger.service.CommentService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CommentDto> getCommentsByPostId(@PathVariable long postId) {
        return commentService.getAllComment(postId);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId) {
        return new ResponseEntity<CommentDto>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long id,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.updateComment(postId, id, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable long postId, @PathVariable long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

}
