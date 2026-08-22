package com.belmontiere.dto.response;

public record UserSummaryResponse(
        Long id,
        String username,
        String locationCity
) {
}
