package com.belmontiere.dto.response;

public record ListingImageResponse(
        Long id,
        String imageUrl,
        int sortOrder
) {
}
