package com.agrawals.blogger.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.agrawals.blogger.dto.PostDto;
import com.agrawals.blogger.dto.PostResponse;
import com.agrawals.blogger.entity.Post;
import com.agrawals.blogger.exception.ResourceNotFound;
import com.agrawals.blogger.repository.PostsRepository;

import lombok.Data;

@Data
@Service
public class PostService implements PostServiceInter {
    private PostsRepository postsRepository;
    private final ModelMapper mapper;

    @Autowired
    public PostService(PostsRepository postsRepository, ModelMapper mapper) {
        this.postsRepository = postsRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postsRepository.save(post);

        PostDto postResponseDto = mapToDto(newPost);
        return postResponseDto;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Post> posts = postsRepository.findAll(pageable);
        PostResponse pResponse = new PostResponse();

        pResponse.setPosts(posts.getContent().stream().map(post -> mapToDto(post)).collect(Collectors.toList()));
        pResponse.setPageNo(posts.getNumber());
        pResponse.setPageSize(posts.getSize());
        pResponse.setTotalElements((int) posts.getTotalElements());
        pResponse.setTotalPages(posts.getTotalPages());
        pResponse.setLastPage(posts.isLast());

        return pResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        postsRepository.save(post);
        return mapToDto(post);
    }

    @Override
    public Boolean deletePost(Long id) {
        postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        postsRepository.deleteById(id);
        return true;
    }

    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }

}
