package com.agrawals.blogger.service;

import org.springframework.stereotype.Service;

import com.agrawals.blogger.dto.PostDto;
import com.agrawals.blogger.dto.PostResponse;

@Service
public interface PostServiceInter {
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);

    PostDto getPostById(Long id);

    PostDto createPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    Boolean deletePost(Long id);
}
