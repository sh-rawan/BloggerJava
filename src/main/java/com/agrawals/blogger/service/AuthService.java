package com.agrawals.blogger.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agrawals.blogger.dto.LoginDto;
import com.agrawals.blogger.dto.RegisterDto;
import com.agrawals.blogger.entity.Role;
import com.agrawals.blogger.entity.User;
import com.agrawals.blogger.exception.BlogApiException;
import com.agrawals.blogger.repository.RoleRepository;
import com.agrawals.blogger.repository.UserRepository;

@Service
public class AuthService implements AuthServiceInter {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return "User Logged-in successfully!!";
    }

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterDto registerDto) {

        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER").get());
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered!!";
    }
}
