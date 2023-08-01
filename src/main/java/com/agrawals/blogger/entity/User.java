package com.agrawals.blogger.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",
    // referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name =
    // "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}

// CREATE TABLE `users` (
// `id` bigint NOT NULL AUTO_INCREMENT,
// `email` varchar(255) NOT NULL,
// `name` varchar(255) NOT NULL,
// `password` varchar(255) NOT NULL,
// `username` varchar(255) NOT NULL,
// PRIMARY KEY (`id`),
// UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
// UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

// CREATE TABLE `users_roles` (
// `user_id` bigint NOT NULL,
// `roles_id` bigint NOT NULL,
// PRIMARY KEY (`user_id`,`roles_id`),
// KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
// CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES
// `users` (`id`),
// CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES
// `roles` (`id`)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
