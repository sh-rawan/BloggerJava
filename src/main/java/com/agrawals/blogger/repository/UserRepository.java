package com.agrawals.blogger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrawals.blogger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);
}
