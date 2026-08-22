package com.belmontiere.dto.response;

import com.belmontiere.entity.enums.UserRole;

import java.time.Instant;

public record UserResponse(
        Long id,
        String username,
        String email,
        String phone,
        String locationCity,
        UserRole role,
        boolean emailVerified,
        Instant createdAt
) {
}
