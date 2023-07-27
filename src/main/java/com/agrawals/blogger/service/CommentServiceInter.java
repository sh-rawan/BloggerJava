package com.agrawals.blogger.service;

import java.util.List;

import com.agrawals.blogger.dto.CommentDto;

public interface CommentServiceInter {
    public CommentDto createComment(long postId, CommentDto commentDto);

    public CommentDto getCommentById(long postId, long commentId);

    public List<CommentDto> getAllComment(long postId);

    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    public void deleteComment(long postId, long commentId);
}
