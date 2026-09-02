package com.belmontiere.controller;

import com.belmontiere.dto.request.RegisterUserRequest;
import com.belmontiere.dto.response.UserResponse;
import com.belmontiere.dto.response.UserSummaryResponse;
import com.belmontiere.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse created = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryResponse> getSummary(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getSummaryById(id));
    }
}