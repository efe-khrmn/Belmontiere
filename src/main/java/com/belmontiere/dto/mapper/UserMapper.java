package com.belmontiere.dto.mapper;

import com.belmontiere.dto.response.UserResponse;
import com.belmontiere.dto.response.UserSummaryResponse;
import com.belmontiere.entity.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserSummaryResponse toSummary(User user) {
        if (user == null) {
            return null;
        }
        return new UserSummaryResponse(
                user.getId(),
                user.getUsername(),
                user.getLocationCity()
        );
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getLocationCity(),
                user.getRole(),
                user.isEmailVerified(),
                user.getCreatedAt()
        );
    }
}
