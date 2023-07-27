package com.agrawals.blogger.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.agrawals.blogger.dto.CommentDto;
import com.agrawals.blogger.entity.Comment;
import com.agrawals.blogger.entity.Post;
import com.agrawals.blogger.exception.BlogApiException;
import com.agrawals.blogger.exception.ResourceNotFound;
import com.agrawals.blogger.repository.CommentRepository;
import com.agrawals.blogger.repository.PostsRepository;

import lombok.Data;

@Service
@Data
public class CommentService implements CommentServiceInter {
    private final CommentRepository commentRepository;
    private final PostsRepository postsRepository;
    private final ModelMapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToComment(commentDto);
        System.out.println(commentDto);
        Post post = postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", "postId"));
        comment.setPost(post);
        Comment commentNew = commentRepository.save(comment);
        System.out.println(commentNew);
        return mapCommentDto(commentNew);
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFound("Post", "Id", "postId"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("Comment", "Id", "commentId"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post!!");
        }
        return mapCommentDto(comment);
    }

    @Override
    public List<CommentDto> getAllComment(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFound("Post", "Id", "postId"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("Comment", "Id", "commentId"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post!!");
        }

        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());

        Comment newComment = commentRepository.save(comment);
        return mapCommentDto(newComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFound("Post", "Id", "postId"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("Comment", "Id", "commentId"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post!!");
        }

        commentRepository.delete(comment);
    }

    private Comment mapToComment(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }

    private CommentDto mapCommentDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
