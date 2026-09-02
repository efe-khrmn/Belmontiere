package com.belmontiere.service;

import com.belmontiere.dto.mapper.UserMapper;
import com.belmontiere.dto.request.RegisterUserRequest;
import com.belmontiere.dto.response.UserResponse;
import com.belmontiere.dto.response.UserSummaryResponse;
import com.belmontiere.entity.User;
import com.belmontiere.exception.DuplicateResourceException;
import com.belmontiere.exception.ResourceNotFoundException;
import com.belmontiere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(RegisterUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("Username is already taken: " + request.username());
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email is already registered: " + request.email());
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .locationCity(request.locationCity())
                .build();

        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserSummaryResponse getSummaryById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return UserMapper.toSummary(user);
    }
}