package com.agrawals.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrawals.blogger.entity.Post;

public interface PostsRepository extends JpaRepository<Post, Long> {
}
