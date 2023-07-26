package com.agrawals.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrawals.blogger.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
